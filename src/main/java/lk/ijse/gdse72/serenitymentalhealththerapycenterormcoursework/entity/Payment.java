package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "payment")
public class Payment implements SuperEntity{
    @Id
    private String payment_id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private TherapyProgram therapy_program;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private TherapySession therapy_session;  // Nullable for upfront payments

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate payment_date;
}