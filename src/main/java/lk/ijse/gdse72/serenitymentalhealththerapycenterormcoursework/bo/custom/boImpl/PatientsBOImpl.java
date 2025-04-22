package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.boImpl;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.PatientsBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.PatientDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientsBOImpl implements PatientsBO {

    PatientDAO patientDAO = (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PATIENT);

    @Override
    public boolean savePatient(PatientDTO patientDTO) throws Exception {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setEmail(patientDTO.getEmail());
        patient.setTherapyHistory(patientDTO.getTherapyHistory());
        patient.setMedicalHistory(patientDTO.getMedicalHistory());
        return patientDAO.save(patient);
    }

    @Override
    public List<PatientDTO> getAllPatients() throws Exception {
        ArrayList<PatientDTO> patientDTOArrayList = new ArrayList<>();
        ArrayList<Patient> patients = patientDAO.getAll();

        for (Patient patient : patients) {
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setPatientId(Integer.parseInt(String.valueOf(patient.getPatientId())));
            patientDTO.setName(patient.getName());
            patientDTO.setDateOfBirth(patient.getDateOfBirth());
            patientDTO.setEmail(patient.getEmail());
            patientDTO.setTherapyHistory(patient.getTherapyHistory());
            patientDTO.setMedicalHistory(patient.getMedicalHistory());
            patientDTOArrayList.add(patientDTO);
        }
        return patientDTOArrayList;
    }

    @Override
    public boolean deletePatient(String id) throws Exception {
        Patient patient = new Patient();
        patient.setPatientId(Integer.parseInt(id));
        return patientDAO.delete(String.valueOf(patient));
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws Exception {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setEmail(patientDTO.getEmail());
        patient.setTherapyHistory(patientDTO.getTherapyHistory());
        patient.setMedicalHistory(patientDTO.getMedicalHistory());
        return patientDAO.update(patient);
    }
}
