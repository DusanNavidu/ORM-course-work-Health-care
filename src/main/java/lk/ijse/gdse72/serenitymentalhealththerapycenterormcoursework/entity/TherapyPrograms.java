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
public class TherapyPrograms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int programID;

    private String programName;
    private String programDuration;

    @Column(precision = 10, scale = 2)
    private double programFee;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;
}

