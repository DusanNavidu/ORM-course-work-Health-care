package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl;



import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapistBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapistDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapistDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Therapist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistBOImpl implements TherapistBO {

    TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.THERAPIST);

    @Override
    public boolean saveTherapist(TherapistDTO dto) {
        Therapist therapist = new Therapist();
        therapist.setTherapist_id(dto.getTherapistId());
        therapist.setName(dto.getName());
        therapist.setEmail(dto.getEmail());
        therapist.setPhone(dto.getPhone());
        therapist.setSpecialization(dto.getSpecialization());

        return therapistDAO.save(therapist);
    }

    @Override
    public boolean updateTherapist(TherapistDTO dto) {
        Therapist therapist = new Therapist();
        therapist.setTherapist_id(dto.getTherapistId());
        therapist.setName(dto.getName());
        therapist.setEmail(dto.getEmail());
        therapist.setPhone(dto.getPhone());
        therapist.setSpecialization(dto.getSpecialization());

        return therapistDAO.update(therapist);
    }

    @Override
    public boolean deleteTherapist(String pk) {
        return therapistDAO.delete(pk);
    }

    @Override
    public ArrayList<TherapistDTO> getAllTherapists() {
        List<Therapist> therapists = therapistDAO.getAll();

        ArrayList<TherapistDTO> therapistDtos = new ArrayList<>();
        for (Therapist therapist : therapists) {
            TherapistDTO therapistDto = new TherapistDTO();
            therapistDto.setTherapistId(therapist.getTherapist_id());
            therapistDto.setName(therapist.getName());
            therapistDto.setEmail(therapist.getEmail());
            therapistDto.setPhone(therapist.getPhone());
            therapistDto.setSpecialization(therapist.getSpecialization());
            therapistDtos.add(therapistDto);
        }
        return therapistDtos;
    }

    @Override
    public ArrayList<TherapistDTO> findByTherapistName(String name) {
        List<Therapist> therapists = therapistDAO.findByTherapistName(name);
        ArrayList<TherapistDTO> therapistDtos = new ArrayList<>();

        for (Therapist therapist : therapists) {
            TherapistDTO therapistDto = new TherapistDTO();
            therapistDto.setTherapistId(therapist.getTherapist_id());
            therapistDto.setName(therapist.getName());
            therapistDto.setEmail(therapist.getEmail());
            therapistDto.setPhone(therapist.getPhone());
            therapistDto.setSpecialization(therapist.getSpecialization());
            therapistDtos.add(therapistDto);
        }

        return therapistDtos;
    }

    @Override
    public TherapistDTO findByTherapistId(String id) {
        Optional<Therapist> therapistOpt = therapistDAO.findById(id);
        if (therapistOpt.isEmpty()) {
            return null;
        }
        Therapist therapist = therapistOpt.get();

        TherapistDTO therapistDto = new TherapistDTO();
        therapistDto.setTherapistId(therapist.getTherapist_id());
        therapistDto.setName(therapist.getName());
        therapistDto.setEmail(therapist.getEmail());
        therapistDto.setPhone(therapist.getPhone());
        therapistDto.setSpecialization(therapist.getSpecialization());

        return therapistDto;
    }

    @Override
    public String getNextTherapistPK() {
        Optional<String> lastPkOpt = therapistDAO.getLastPK();

        if (lastPkOpt.isPresent()) {
            String lastPk = lastPkOpt.get();
            String numericPart = lastPk.substring(1);
            int currentId = Integer.parseInt(numericPart);
            int nextId = currentId + 1;

            return String.format("T%03d", nextId);
        }

        return "T001";
    }

}
