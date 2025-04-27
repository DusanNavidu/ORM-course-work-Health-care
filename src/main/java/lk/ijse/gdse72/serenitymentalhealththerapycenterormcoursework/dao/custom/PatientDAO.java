package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;



import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO extends CrudDAO<Patient> {
    public List<Patient> findByPatientName(String name);
    public Optional<Patient> findById(String pk);
}
