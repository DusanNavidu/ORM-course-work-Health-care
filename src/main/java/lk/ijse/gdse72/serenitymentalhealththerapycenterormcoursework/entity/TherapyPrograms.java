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
    private int ProgramID;
    private String ProgramName;
    private String programDuration;
    private double programFee;
}
