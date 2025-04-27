package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Therapist;

import java.util.List;
import java.util.Optional;

public interface TherapistDAO extends CrudDAO<Therapist> {

    public List<Therapist> findByTherapistName(String name);
    public Optional<Therapist> findById(String id);

}
