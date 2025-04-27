package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl;



import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.PatientBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.PatientDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PATIENT);

    @Override
    public boolean savePatient(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setPatient_id(dto.getPatientId());
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        patient.setMedical_history(dto.getMedicalHistory());

        return patientDAO.save(patient);
    }

    @Override
    public boolean updatePatient(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setPatient_id(dto.getPatientId());
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        patient.setMedical_history(dto.getMedicalHistory());

        return patientDAO.update(patient);
    }


    @Override
    public boolean deletePatient(String pk) {
        return patientDAO.delete(pk);
    }

    @Override
    public ArrayList<PatientDTO> getAllPatients() {
        List<Patient> patients = patientDAO.getAll();

        ArrayList<PatientDTO> patientDtos = new ArrayList<>();
        for (Patient patient : patients) {
            PatientDTO patientDto = new PatientDTO();
            patientDto.setPatientId(patient.getPatient_id());
            patientDto.setName(patient.getName());
            patientDto.setEmail(patient.getEmail());
            patientDto.setPhone(patient.getPhone());
            patientDto.setAddress(patient.getAddress());
            patientDto.setMedicalHistory(patient.getMedical_history());
            patientDtos.add(patientDto);
        }
        return patientDtos;
    }



    @Override
    public ArrayList<PatientDTO> findByPatientName(String name) {
        List<Patient> patients = patientDAO.findByPatientName(name);
        ArrayList<PatientDTO> patientDtos = new ArrayList<>();

        for (Patient patient : patients) {
            PatientDTO patientDto = new PatientDTO();
            patientDto.setPatientId(patient.getPatient_id());
            patientDto.setName(patient.getName());
            patientDto.setEmail(patient.getEmail());
            patientDto.setPhone(patient.getPhone());
            patientDto.setAddress(patient.getAddress());
            patientDto.setMedicalHistory(patient.getMedical_history());
            patientDtos.add(patientDto);
        }

        return patientDtos;
    }

    @Override
    public PatientDTO findPatientByID(String id) {
        Optional<Patient> patientOpt = patientDAO.findById(id);

        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            return new PatientDTO(
                    patient.getPatient_id(),
                    patient.getName(),
                    patient.getEmail(),
                    patient.getPhone(),
                    patient.getAddress(),
                    patient.getMedical_history()
            );
        }

        return null;
    }




    @Override
    public String getNextPatientPK() {
        Optional<String> lastPkOpt = patientDAO.getLastPK();

        if (lastPkOpt.isPresent()) {
            String lastPk = lastPkOpt.get();
            String numericPart = lastPk.substring(1);
            int currentId = Integer.parseInt(numericPart);
            int nextId = currentId + 1;

            return String.format("P%03d", nextId);
        }

        return "P001";
    }



}
