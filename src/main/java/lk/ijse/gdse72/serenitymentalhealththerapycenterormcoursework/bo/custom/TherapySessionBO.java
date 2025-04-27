package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapySessionDTO;

import java.util.List;

public interface TherapySessionBO extends SuperBO {
    boolean save(TherapySessionDTO dto);
    boolean update(TherapySessionDTO dto);
    boolean delete(String sessionId);
    List<TherapySessionDTO> getAll();
    TherapySessionDTO findBySessionId(String sessionId);
    List<TherapySessionDTO> findByPatientId(String patientId);
    String getNextSessionPK();
}
