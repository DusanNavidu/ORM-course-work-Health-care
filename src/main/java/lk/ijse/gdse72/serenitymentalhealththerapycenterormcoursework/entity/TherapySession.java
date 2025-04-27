package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "therapy_session")
public class TherapySession implements SuperEntity {

    @Id
    private String session_id;

    @ManyToOne
    @JoinColumn(name = "therapist_id", nullable = false)
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private TherapyProgram therapy_program;

    @OneToOne
    @JoinColumn(name = "availability_id")
    private TherapistAvailability therapistAvailability;

    @Column(nullable = false)
    private LocalDate session_date;

    @Column(nullable = false)
    private LocalTime start_time;

    @Column(nullable = false)
    private int duration; // "30 minutes", "1 hour", etc.

    @Column(nullable = false)
    private String status;  // "Scheduled", "Completed", "Cancelled"



}
