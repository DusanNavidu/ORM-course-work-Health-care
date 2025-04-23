package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;

public interface PatientDAO extends CrudDAO<Patient, String> {

    String getPatientNameById(String patientId) throws Exception;
}
