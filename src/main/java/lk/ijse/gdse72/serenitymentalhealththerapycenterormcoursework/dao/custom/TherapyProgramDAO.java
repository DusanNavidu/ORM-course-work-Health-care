package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapyProgram;

import java.util.List;
import java.util.Optional;

public interface TherapyProgramDAO extends CrudDAO<TherapyProgram> {

    public List<TherapyProgram> findByTherapyProgramName(String name);
    public Optional<TherapyProgram> findById(String pk);

}
