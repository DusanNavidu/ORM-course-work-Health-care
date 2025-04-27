package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;



import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;

import java.util.ArrayList;


public interface PatientBO extends SuperBO {

    public boolean savePatient(PatientDTO dto);
    public boolean updatePatient(PatientDTO dto);
    public boolean deletePatient(String pk);
    public ArrayList<PatientDTO> getAllPatients();
    public ArrayList<PatientDTO> findByPatientName(String name);
    public String getNextPatientPK();
    public PatientDTO findPatientByID(String id);


}
