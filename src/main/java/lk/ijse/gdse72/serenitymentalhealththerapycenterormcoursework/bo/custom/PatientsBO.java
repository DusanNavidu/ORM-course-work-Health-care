package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;

import java.sql.SQLException;
import java.util.List;

public interface PatientsBO {

    boolean savePatient(PatientDTO patientDTO) throws Exception;

    List<PatientDTO> getAllPatients() throws Exception;

    boolean deletePatient(String id) throws Exception;

    boolean updatePatient(PatientDTO patientDTO) throws Exception;
}
