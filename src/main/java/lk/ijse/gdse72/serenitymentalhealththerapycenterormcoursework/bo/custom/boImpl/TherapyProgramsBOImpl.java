package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.boImpl;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapyProgramsBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapyProgramsDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramsDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapyPrograms;

import java.util.ArrayList;
import java.util.List;

public class TherapyProgramsBOImpl implements TherapyProgramsBO {

    TherapyProgramsDAO therapyProgramsDAO = (TherapyProgramsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);

    @Override
    public boolean saveTherapyProgram(TherapyProgramsDTO therapyProgramsDTO) throws Exception {
        TherapyPrograms therapyPrograms = new TherapyPrograms();
        therapyPrograms.setProgramName(therapyProgramsDTO.getProgramName());
        therapyPrograms.setProgramDuration(therapyProgramsDTO.getProgramDuration());
        therapyPrograms.setProgramFee(therapyProgramsDTO.getProgramFee());
        return therapyProgramsDAO.save(therapyPrograms);
    }

    @Override
    public List<TherapyProgramsDTO> getAllTherapyPrograms() throws Exception {
        ArrayList<TherapyProgramsDTO> therapyProgramsDTOArrayList = new ArrayList<>();
        ArrayList<TherapyPrograms> therapyPrograms = therapyProgramsDAO.getAll();

        for (TherapyPrograms therapyProgram : therapyPrograms) {
            TherapyProgramsDTO therapyProgramsDTO = new TherapyProgramsDTO();
            therapyProgramsDTO.setProgramID(Integer.parseInt(String.valueOf(therapyProgram.getProgramID())));
            therapyProgramsDTO.setProgramName(therapyProgram.getProgramName());
            therapyProgramsDTO.setProgramDuration(therapyProgram.getProgramDuration());
            therapyProgramsDTO.setProgramFee(therapyProgram.getProgramFee());
            therapyProgramsDTOArrayList.add(therapyProgramsDTO);
        }
        return therapyProgramsDTOArrayList;
    }

    @Override
    public boolean deleteTherapyProgram(String id) throws Exception {
        TherapyPrograms therapyPrograms = new TherapyPrograms();
        therapyPrograms.setProgramID(Integer.parseInt(id));
        return therapyProgramsDAO.delete(String.valueOf(therapyPrograms));
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramsDTO therapyProgramsDTO) throws Exception {
        TherapyPrograms therapyPrograms = new TherapyPrograms();
        therapyPrograms.setProgramName(therapyProgramsDTO.getProgramName());
        therapyPrograms.setProgramDuration(therapyProgramsDTO.getProgramDuration());
        therapyPrograms.setProgramFee(therapyProgramsDTO.getProgramFee());
        return therapyProgramsDAO.update(therapyPrograms);
    }
}
