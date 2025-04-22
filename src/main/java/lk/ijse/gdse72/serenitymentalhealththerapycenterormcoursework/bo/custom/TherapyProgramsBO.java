package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramsDTO;

import java.util.List;

public interface TherapyProgramsBO {

    boolean saveTherapyProgram(TherapyProgramsDTO therapyProgramsDTO) throws Exception;

    List<TherapyProgramsDTO> getAllTherapyPrograms() throws Exception;

    boolean deleteTherapyProgram(String id) throws Exception;

    boolean updateTherapyProgram(TherapyProgramsDTO therapyProgramsDTO) throws Exception;
}
