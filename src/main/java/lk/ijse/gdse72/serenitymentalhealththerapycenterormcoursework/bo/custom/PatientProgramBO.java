package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientProgramDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface PatientProgramBO extends SuperBO {

    public boolean savePatientProgram(PatientProgramDTO dto);
    public boolean updatePatientProgram(PatientProgramDTO dto);
    public boolean deletePatientProgram(String patientName, String programName);
    public ArrayList<PatientProgramDTO> getAllPatientPrograms();
    public PatientDTO findByPatientName(String patientName);
    public TherapyProgramDTO findByProgramName(String programName);
    public ArrayList<PatientProgramDTO> search(String name, boolean isPatient);
    public String getNextPatientProgramPK();

    public List<PatientProgramDTO> getProgramsByPatientId(String patientId);
    public List<PatientProgramDTO> getPatientsByProgramId(String programId);

    public boolean updateTherapyProgramFeeOfPatient(String patientId, String programId, BigDecimal newFee);
    public PatientProgramDTO searchPatientProgramFromBothIds(String patientId, String programId);

}
