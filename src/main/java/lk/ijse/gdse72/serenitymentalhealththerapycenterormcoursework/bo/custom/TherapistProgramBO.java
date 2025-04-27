package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapistProgramDTO;

import java.util.List;

public interface TherapistProgramBO extends SuperBO {
    public boolean saveTherapistProgram(String therapistId, String programId);
    public boolean updateTherapistProgram(String therapistId, String programId);
    public boolean deleteTherapistProgram(String therapistId, String programId);
    public TherapistProgramDTO findById(String therapistId, String programId);
    public List<TherapistProgramDTO> getAllTherapistPrograms();
    public List<TherapistProgramDTO> findByProgramName(String name);
    public List<TherapistProgramDTO> getTherapistProgramsByTherapist(String id);

    List<TherapistProgramDTO> getTherapistProgramsByTherapistId(String therapistId);
    List<TherapistProgramDTO> getTherapistProgramsByProgramId(String programId);


}
