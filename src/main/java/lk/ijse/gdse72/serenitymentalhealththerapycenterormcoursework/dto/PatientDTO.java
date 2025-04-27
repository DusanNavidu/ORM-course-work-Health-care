package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDTO {

    private String patientId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String medicalHistory;

}
