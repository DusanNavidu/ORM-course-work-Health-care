package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private int patientId;
    private String name;
    private String dateOfBirth;
    private String email;
    private String TherapyHistory;
    private String medicalHistory;

    public PatientDTO(String name, String email, String dateOfBirth, String TherapyHistory, String medicalHistory) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.TherapyHistory = TherapyHistory;
        this.medicalHistory = medicalHistory;
    }
}