package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapySession;

import java.util.List;
import java.util.Optional;

public interface TherapySessionDAO extends CrudDAO<TherapySession> {

    Optional<TherapySession> findBySessionId(String sessionId);
    List<TherapySession> findByPatientId(String patientId);


}
