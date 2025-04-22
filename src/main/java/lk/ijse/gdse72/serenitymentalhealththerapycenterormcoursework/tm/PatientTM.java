package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientTM {
    private int patientId;
    private String name;
    private String dateOfBirth;
    private String email;
    private String TherapyHistory;
    private String medicalHistory;
}