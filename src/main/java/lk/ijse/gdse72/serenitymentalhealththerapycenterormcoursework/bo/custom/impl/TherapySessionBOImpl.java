package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.BOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapistAvailabilityBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapySessionBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.config.FactoryConfiguration;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.PatientDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapistDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapySessionDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapySessionDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Therapist;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapyProgram;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapySessionBOImpl implements TherapySessionBO {

    TherapySessionDAO therapySessionDAO = (TherapySessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_SESSION);
    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);
    TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);

    TherapistAvailabilityBO therapistAvailabilityBO = (TherapistAvailabilityBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST_AVAILABILITY);

    @Override
    public boolean save(TherapySessionDTO dto) {
        boolean isCompleted = false;

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Retrieve the entities from their respective DAOs
            Optional<Therapist> therapistOpt = therapistDAO.findById(dto.getTherapistId());
            Optional<Patient> patientOpt = patientDAO.findById(dto.getPatientId());
            Optional<TherapyProgram> programOpt = therapyProgramDAO.findById(dto.getTherapyProgramId());

//            Optional<TherapistAvailability> availabilityOpt = availabilityDAO.findById(dto.getAvailabilityId());

            // Check if any of the required entities are not found
            if (therapistOpt.isEmpty() || patientOpt.isEmpty() || programOpt.isEmpty()) {
                return false;
            }

            // Create the TherapySession entity
            TherapySession therapySession = new TherapySession();
            therapySession.setSession_id(dto.getSessionId());
            therapySession.setTherapist(therapistOpt.get());
            therapySession.setPatient(patientOpt.get());
            therapySession.setTherapy_program(programOpt.get());
            therapySession.setTherapistAvailability(null); // Set null because we can not get the availability object
            therapySession.setSession_date(dto.getSessionDate());
            therapySession.setStart_time(dto.getSessionTime());
            therapySession.setDuration(dto.getDuration());
            therapySession.setStatus(dto.getStatus());


            // Convert the duration (in minutes) to a Duration object
            Duration sessionDuration = Duration.ofMinutes(dto.getDuration());

            // Attempt to book the time slot
            boolean success = therapistAvailabilityBO.bookTimeSlot(
                    dto.getTherapistId(),
                    dto.getSessionDate(),
                    dto.getSessionTime(),
                    sessionDuration
            );

            if (success) {
                if (therapySessionDAO.save(therapySession)) {
                    isCompleted = true;
                    transaction.commit();
                }
            } else {
                isCompleted = false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isCompleted;
    }

    @Override
    public boolean update(TherapySessionDTO dto) {
        Optional<Therapist> therapistOpt = therapistDAO.findById(dto.getTherapistId());
        Optional<Patient> patientOpt = patientDAO.findById(dto.getPatientId());
        Optional<TherapyProgram> programOpt = therapyProgramDAO.findById(dto.getTherapyProgramId());
        Optional<TherapySession> optionalSession = therapySessionDAO.findBySessionId(dto.getSessionId());

        if (therapistOpt.isEmpty() || patientOpt.isEmpty() || programOpt.isEmpty() || optionalSession.isEmpty()) {
            return false;
        }

        TherapySession therapySession = optionalSession.get();

        therapySession.setTherapist(therapistOpt.get());
        therapySession.setPatient(patientOpt.get());
        therapySession.setTherapy_program(programOpt.get());

        if (dto.getAvailabilityId() != null) {
            therapySession.setTherapistAvailability(null);
        } else {
            therapySession.setTherapistAvailability(null); // Optional: reset if null
        }

        therapySession.setSession_date(dto.getSessionDate());
        therapySession.setStart_time(dto.getSessionTime());
        therapySession.setDuration(dto.getDuration());
        therapySession.setStatus(dto.getStatus());

        return therapySessionDAO.update(therapySession);
    }

    @Override
    public boolean delete(String sessionId) {
        Optional<TherapySession> optionalSession = therapySessionDAO.findBySessionId(sessionId);

        if (optionalSession.isEmpty()) return false;
        TherapySession session = optionalSession.get();

        boolean restored = therapistAvailabilityBO.restoreTimeSlot(
                session.getTherapist().getTherapist_id(),
                session.getSession_date(),
                session.getStart_time(),
                Duration.ofMinutes(session.getDuration())
        );

        if (!restored) return false;
        return therapySessionDAO.delete(sessionId);
    }


    @Override
    public ArrayList<TherapySessionDTO> getAll() {
        List<TherapySession> sessions = therapySessionDAO.getAll();
        ArrayList<TherapySessionDTO> sessionDtos = new ArrayList<>();

        for (TherapySession session : sessions) {
            TherapySessionDTO dto = new TherapySessionDTO();
            dto.setSessionId(session.getSession_id());
            dto.setTherapistId(session.getTherapist().getTherapist_id());
            dto.setPatientId(session.getPatient().getPatient_id());
            dto.setTherapyProgramId(session.getTherapy_program().getProgram_id());
            dto.setAvailabilityId(session.getTherapistAvailability() != null
                    ? session.getTherapistAvailability().getAvailability_id() : null);
            dto.setSessionDate(session.getSession_date());
            dto.setSessionTime(session.getStart_time());
            dto.setDuration(session.getDuration());
            dto.setStatus(session.getStatus());

            sessionDtos.add(dto);
        }

        return sessionDtos;
    }


    @Override
    public TherapySessionDTO findBySessionId(String sessionId) {
        Optional<TherapySession> optional = therapySessionDAO.findBySessionId(sessionId);
        if (optional.isEmpty()) return null;

        TherapySession session = optional.get();
        TherapySessionDTO dto = new TherapySessionDTO();
        dto.setSessionId(session.getSession_id());
        dto.setTherapistId(session.getTherapist().getTherapist_id());
        dto.setPatientId(session.getPatient().getPatient_id());
        dto.setTherapyProgramId(session.getTherapy_program().getProgram_id());
        dto.setAvailabilityId(session.getTherapistAvailability() != null
                ? session.getTherapistAvailability().getAvailability_id() : null);
        dto.setSessionDate(session.getSession_date());
        dto.setSessionTime(session.getStart_time());
        dto.setDuration(session.getDuration());
        dto.setStatus(session.getStatus());

        return dto;
    }


    @Override
    public ArrayList<TherapySessionDTO> findByPatientId(String patientId) {
        List<TherapySession> sessions = therapySessionDAO.findByPatientId(patientId);
        ArrayList<TherapySessionDTO> sessionDtos = new ArrayList<>();

        for (TherapySession session : sessions) {
            TherapySessionDTO dto = new TherapySessionDTO();
            dto.setSessionId(session.getSession_id());
            dto.setTherapistId(session.getTherapist().getTherapist_id());
            dto.setPatientId(session.getPatient().getPatient_id());
            dto.setTherapyProgramId(session.getTherapy_program().getProgram_id());
            dto.setAvailabilityId(session.getTherapistAvailability() != null
                    ? session.getTherapistAvailability().getAvailability_id() : null);
            dto.setSessionDate(session.getSession_date());
            dto.setSessionTime(session.getStart_time());
            dto.setDuration(session.getDuration());
            dto.setStatus(session.getStatus());

            sessionDtos.add(dto);
        }

        return sessionDtos;
    }


    @Override
    public String getNextSessionPK() {
        Optional<String> lastPkOpt = therapySessionDAO.getLastPK();

        if (lastPkOpt.isPresent()) {
            String lastPk = lastPkOpt.get();
            String numericPart = lastPk.substring(2);
            int currentId = Integer.parseInt(numericPart);
            int nextId = currentId + 1;
            return String.format("TS%03d", nextId);
        }

        return "TS001";
    }



}
