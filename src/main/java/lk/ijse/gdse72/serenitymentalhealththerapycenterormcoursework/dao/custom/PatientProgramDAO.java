package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.PatientProgram;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PatientProgramDAO extends CrudDAO<PatientProgram> {

    boolean delete(String patientId, String programId);
    List<PatientProgram> searchByName(String name);
    List<PatientProgram> findByPatientId(String id);
    List<PatientProgram> findByProgramId(String id);
    Optional<PatientProgram> findById(String patientId, String programId);
    boolean updateTherapyProgramFee(String patientId, String programId, BigDecimal newFee);


}
