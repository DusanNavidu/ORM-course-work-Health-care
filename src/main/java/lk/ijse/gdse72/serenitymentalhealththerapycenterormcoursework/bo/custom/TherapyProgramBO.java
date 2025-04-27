package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramDTO;

import java.util.ArrayList;

public interface TherapyProgramBO extends SuperBO {

    public boolean saveTherapyProgram(TherapyProgramDTO dto);
    public boolean updateTherapyProgram(TherapyProgramDTO dto);
    public boolean deleteTherapyProgram(String programId);
    public ArrayList<TherapyProgramDTO> getAllTherapyPrograms();
    public ArrayList<TherapyProgramDTO> findTherapyProgramByName(String name);
    public String getNextTherapyProgramPK();
    public TherapyProgramDTO findTherapyProgramByID(String id);

}
