package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl;



import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapistAvailabilityBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapistAvailabilityDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapistDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapistAvailabilityDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Therapist;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapistAvailability;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


public class TherapistAvailabilityBOImpl implements TherapistAvailabilityBO {


    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    TherapistAvailabilityDAO therapistAvailabilityDAO = (TherapistAvailabilityDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST_AVAILABILITY);


    public boolean saveTherapistAvailability(TherapistAvailabilityDTO dto) {
        Optional<Therapist> therapistOtp = therapistDAO.findById(dto.getTherapistId());

        if (therapistOtp.isEmpty()) {
            return false;
        }

        Therapist therapist = therapistOtp.get();
        TherapistAvailability availability = new TherapistAvailability();
        availability.setAvailability_id(generateNextId());
        availability.setTherapist(therapist);
        availability.setAvailable_date(dto.getAvailableDate());
        availability.setStart_time(dto.getStartTime());
        availability.setEnd_time(dto.getEndTime());
        availability.setAvailable_slots(generateSlots(dto.getStartTime(), dto.getEndTime(), Duration.ofMinutes(30)));
        availability.set_available(true);

        return therapistAvailabilityDAO.save(availability);
    }


    public boolean updateTherapistAvailability(TherapistAvailabilityDTO dto) {
        Optional<Therapist> therapistOtp = therapistDAO.findById(dto.getTherapistId());

        if (therapistOtp.isEmpty()) {
            return false;
        }

        Therapist therapist = therapistOtp.get();
        TherapistAvailability availability = new TherapistAvailability();
        availability.setAvailability_id(dto.getAvailabilityId());
        availability.setTherapist(therapist);
        availability.setAvailable_date(dto.getAvailableDate());
        availability.setStart_time(dto.getStartTime());
        availability.setEnd_time(dto.getEndTime());
        availability.setAvailable_slots(dto.getAvailableSlots());
        availability.set_available(dto.isAvailable());

        return therapistAvailabilityDAO.update(availability);
    }


    public boolean deleteAvailability(String availabilityId) {
        return therapistAvailabilityDAO.delete(availabilityId);
    }

    public List<TherapistAvailabilityDTO> getAllAvailability() {
        List<TherapistAvailability> entities = therapistAvailabilityDAO.getAll();
        List<TherapistAvailabilityDTO> dtos = new ArrayList<>();
        for (TherapistAvailability entity : entities) {
            dtos.add(new TherapistAvailabilityDTO(
                    entity.getAvailability_id(),
                    entity.getTherapist().getTherapist_id(),
                    entity.getAvailable_date(),
                    entity.getStart_time(),
                    entity.getEnd_time(),
                    null,
                    entity.is_available()
            ));
        }
        return dtos;
    }



    public List<TherapistAvailabilityDTO> findByTherapistId(String therapistId) {
        List<TherapistAvailability> entities = therapistAvailabilityDAO.findByTherapistId(therapistId);
        List<TherapistAvailabilityDTO> dtos = new ArrayList<>();

        for (TherapistAvailability entity : entities) {
            dtos.add(new TherapistAvailabilityDTO(
                    entity.getAvailability_id(),
                    entity.getTherapist().getTherapist_id(),
                    entity.getAvailable_date(),
                    entity.getStart_time(),
                    entity.getEnd_time(),
                    entity.getAvailable_slots(),
                    entity.is_available()
            ));
        }

        return dtos;
    }

    // === Find by date ===
    public List<TherapistAvailabilityDTO> findByDate(LocalDate date) {
        List<TherapistAvailability> entities = therapistAvailabilityDAO.findByDate(date);
        List<TherapistAvailabilityDTO> dtos = new ArrayList<>();

        for (TherapistAvailability entity : entities) {
            dtos.add(new TherapistAvailabilityDTO(
                    entity.getAvailability_id(),
                    entity.getTherapist().getTherapist_id(),
                    entity.getAvailable_date(),
                    entity.getStart_time(),
                    entity.getEnd_time(),
                    entity.getAvailable_slots(),
                    entity.is_available()
            ));
        }

        return dtos;
    }

    // === Find by therapist ID + date ===
    public List<TherapistAvailabilityDTO> findByTherapistAndDate(String therapistId, LocalDate date) {
        List<TherapistAvailability> entities = therapistAvailabilityDAO.findByTherapistAndDate(therapistId, date);

        if (entities.isEmpty()) return new ArrayList<>();

        List<TherapistAvailabilityDTO> dtos = new ArrayList<>();
        for (TherapistAvailability entity : entities) {
            dtos.add(new TherapistAvailabilityDTO(
                    entity.getAvailability_id(),
                    entity.getTherapist().getTherapist_id(),
                    entity.getAvailable_date(),
                    entity.getStart_time(),
                    entity.getEnd_time(),
                    entity.getAvailable_slots(),
                    entity.is_available()
            ));
        }

        return dtos;
    }


    // === Book time slot ===
    public boolean bookTimeSlot(String therapistId, LocalDate date, LocalTime startTime, Duration sessionDuration) {
        List<TherapistAvailability> availabilityList = therapistAvailabilityDAO.findByTherapistAndDate(therapistId, date);

        if (availabilityList.isEmpty()) return false;

        Duration slotDuration = Duration.ofMinutes(30);
        int requiredSlotCount = (int) (sessionDuration.toMinutes() / slotDuration.toMinutes());

        for (TherapistAvailability availability : availabilityList) {
            List<String> availableSlots = availability.getAvailable_slots();

            // Find the index of the slot starting at the given startTime
            int startIndex = -1;
            for (int i = 0; i < availableSlots.size(); i++) {
                String slotStart = availableSlots.get(i).split("-")[0];
                if (LocalTime.parse(slotStart).equals(startTime)) {
                    startIndex = i;
                    break;
                }
            }

            if (startIndex != -1 && startIndex + requiredSlotCount <= availableSlots.size()) {
                List<String> subList = availableSlots.subList(startIndex, startIndex + requiredSlotCount);

                if (areConsecutive(subList, slotDuration)) {
                    // Book these slots
                    availability.getAvailable_slots().removeAll(subList);

                    // Mark as unavailable if no slots left
                    if (availability.getAvailable_slots().isEmpty()) {
                        availability.set_available(false);
                    }

                    return therapistAvailabilityDAO.update(availability);
                }
            }
        }
        return false;
    }



    private boolean areConsecutive(List<String> slots, Duration slotDuration) {
        for (int i = 0; i < slots.size() - 1; i++) {
            String currentEnd = slots.get(i).split("-")[1];
            String nextStart = slots.get(i + 1).split("-")[0];

            if (!LocalTime.parse(currentEnd).equals(LocalTime.parse(nextStart))) {
                return false;
            }
        }

        String firstStart = slots.get(0).split("-")[0];
        String lastEnd = slots.get(slots.size() - 1).split("-")[1];

        Duration totalDuration = Duration.between(LocalTime.parse(firstStart), LocalTime.parse(lastEnd));
        return totalDuration.equals(slotDuration.multipliedBy(slots.size()));
    }

    // === Slot Generator ===
    public static List<String> generateSlots(LocalTime start, LocalTime end, Duration duration) {
        List<String> slots = new ArrayList<>();
        LocalTime current = start;

        while (!current.plus(duration).isAfter(end)) {
            LocalTime slotEnd = current.plus(duration);
            slots.add(current + "-" + slotEnd);
            current = slotEnd;
        }

        return slots;
    }

    // === Generate Next ID (simple UUID approach) ===
    public String generateNextId() {
        return UUID.randomUUID().toString();
    }


    public boolean restoreTimeSlot(String therapistId, LocalDate date, LocalTime startTime, Duration sessionDuration) {
        List<TherapistAvailability> availabilityList = therapistAvailabilityDAO.findByTherapistAndDate(therapistId, date);

        if (availabilityList.isEmpty()) return false;

        Duration slotDuration = Duration.ofMinutes(30);
        int slotCount = (int) (sessionDuration.toMinutes() / slotDuration.toMinutes());

        // Generate the slots to restore
        List<String> slotsToRestore = new ArrayList<>();
        LocalTime current = startTime;
        for (int i = 0; i < slotCount; i++) {
            String slot = current + "-" + current.plus(slotDuration);
            slotsToRestore.add(slot);
            current = current.plus(slotDuration);
        }

        for (TherapistAvailability availability : availabilityList) {
            if (availability.getAvailable_date().equals(date)) {
                List<String> existingSlots = availability.getAvailable_slots();

                // Add the slots back
                existingSlots.addAll(slotsToRestore);
                existingSlots.sort(Comparator.comparing(slot -> LocalTime.parse(slot.split("-")[0])));

                availability.set_available(true); // Restore availability
                return therapistAvailabilityDAO.update(availability);
            }
        }

        return false;
    }


    @Override
    public String getNextPK() {
        return generateNextId();
    }

    @Override
    public ArrayList<TherapistAvailabilityDTO> getAllAvailabilities() {
        return new ArrayList<>(getAllAvailability());
    }

    @Override
    public ArrayList<TherapistAvailabilityDTO> findByTherapist(String therapistName) {
        // This would typically search by therapist name, but we'll use ID for now
        // In a real implementation, you would query the therapist by name first
        return new ArrayList<>(findByTherapistId(therapistName));
    }

    @Override
    public boolean saveAvailability(TherapistAvailabilityDTO dto) {
        return saveTherapistAvailability(dto);
    }

    @Override
    public boolean updateAvailability(TherapistAvailabilityDTO dto) {
        return updateTherapistAvailability(dto);
    }
}
