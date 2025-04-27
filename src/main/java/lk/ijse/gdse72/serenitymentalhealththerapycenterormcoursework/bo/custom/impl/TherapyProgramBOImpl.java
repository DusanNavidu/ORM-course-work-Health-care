package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapyProgramBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapyProgramBOImpl implements TherapyProgramBO {

    TherapyProgramDAO therapyProgramDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);

    @Override
    public boolean saveTherapyProgram(TherapyProgramDTO dto) {
        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setProgram_id(dto.getProgramId());
        therapyProgram.setName(dto.getName());
        therapyProgram.setDuration(dto.getDuration());
        therapyProgram.setFee(dto.getFee());
        therapyProgram.setDescription(dto.getDescription());

        return therapyProgramDAO.save(therapyProgram);
    }

    @Override
    public boolean updateTherapyProgram(TherapyProgramDTO dto) {
        TherapyProgram therapyProgram = new TherapyProgram();
        therapyProgram.setProgram_id(dto.getProgramId());
        therapyProgram.setName(dto.getName());
        therapyProgram.setDuration(dto.getDuration());
        therapyProgram.setFee(dto.getFee());
        therapyProgram.setDescription(dto.getDescription());

        return therapyProgramDAO.update(therapyProgram);
    }

    @Override
    public boolean deleteTherapyProgram(String programId) {
        return therapyProgramDAO.delete(programId);
    }

    @Override
    public ArrayList<TherapyProgramDTO> getAllTherapyPrograms() {
        List<TherapyProgram> programs = therapyProgramDAO.getAll();
        ArrayList<TherapyProgramDTO> programDtos = new ArrayList<>();

        for (TherapyProgram program : programs) {
            TherapyProgramDTO dto = new TherapyProgramDTO();
            dto.setProgramId(program.getProgram_id());
            dto.setName(program.getName());
            dto.setDuration(program.getDuration());
            dto.setFee(program.getFee());
            dto.setDescription(program.getDescription());
            programDtos.add(dto);
        }
        return programDtos;
    }

    @Override
    public ArrayList<TherapyProgramDTO> findTherapyProgramByName(String name) {
        List<TherapyProgram> programs = therapyProgramDAO.findByTherapyProgramName(name);
        ArrayList<TherapyProgramDTO> dtos = new ArrayList<>();

        for (TherapyProgram program : programs) {
            dtos.add(new TherapyProgramDTO(
                    program.getProgram_id(),
                    program.getName(),
                    program.getDuration(),
                    program.getFee(),
                    program.getDescription()
            ));
        }
        return dtos;
    }

    @Override
    public TherapyProgramDTO findTherapyProgramByID(String id) {
        Optional<TherapyProgram> programOpt = therapyProgramDAO.findById(id);

        if (programOpt.isPresent()) {
            TherapyProgram program = programOpt.get();
            return new TherapyProgramDTO(
                    program.getProgram_id(),
                    program.getName(),
                    program.getDuration(),
                    program.getFee(),
                    program.getDescription()
            );
        }
        return null;
    }


    @Override
    public String getNextTherapyProgramPK() {
        Optional<String> lastPkOpt = therapyProgramDAO.getLastPK();

        if (lastPkOpt.isPresent()) {
            String lastPk = lastPkOpt.get();
            if (lastPk.startsWith("TP")) {
                String numericPart = lastPk.substring(2);

                try {
                    int currentId = Integer.parseInt(numericPart);
                    int nextId = currentId + 1;

                    return String.format("TP%03d", nextId);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing numeric part of primary key: " + numericPart);
                    return "TP001";
                }
            }
        }
        return "TP001";
    }
}
