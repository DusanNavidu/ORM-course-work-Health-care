package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapistAvailabilityDTO;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface TherapistAvailabilityBO extends SuperBO {

    public boolean saveTherapistAvailability(TherapistAvailabilityDTO dto);
    public boolean updateTherapistAvailability(TherapistAvailabilityDTO dto);
    public boolean deleteAvailability(String availabilityId);
    public List<TherapistAvailabilityDTO> getAllAvailability();
    public List<TherapistAvailabilityDTO> findByTherapistId(String therapistId);
    public List<TherapistAvailabilityDTO> findByDate(LocalDate date);
    public List<TherapistAvailabilityDTO> findByTherapistAndDate(String therapistId, LocalDate date);
    public boolean bookTimeSlot(String therapistId, LocalDate date, LocalTime startTime, Duration sessionDuration);
    public String generateNextId();

    public boolean restoreTimeSlot(String therapistId, LocalDate date, LocalTime startTime, Duration sessionDuration);

        // Add methods needed by the controller
        public String getNextPK();
        public ArrayList<TherapistAvailabilityDTO> getAllAvailabilities();
        public ArrayList<TherapistAvailabilityDTO> findByTherapist(String therapistName);
        public boolean saveAvailability(TherapistAvailabilityDTO dto);
        public boolean updateAvailability(TherapistAvailabilityDTO dto);

}
