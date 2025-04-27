package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapistProgram;

import java.util.List;
import java.util.Optional;

public interface TherapistProgramDAO extends CrudDAO<TherapistProgram> {

    boolean delete(String therapistId, String programId);
    List<TherapistProgram> findByProgramName(String name);
    List<TherapistProgram> findByTherapist(String name);
    Optional<TherapistProgram> findById(String therapistId, String programId);
    List<TherapistProgram> findByTherapistId(String therapistId);
    List<TherapistProgram> findByProgramId(String programId);

}
