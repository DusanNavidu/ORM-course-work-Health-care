package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paymentId;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Temporal(TemporalType.TIME)
    private Date paymentTime;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyPrograms program;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String amount;
}
