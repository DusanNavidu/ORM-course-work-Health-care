package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int patientId;
    private String name;
    private String dateOfBirth;
    private String email;
    private String TherapyHistory;
    private String medicalHistory;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Appointment> appointmentList;
//
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<TherapySession> sessions;
}