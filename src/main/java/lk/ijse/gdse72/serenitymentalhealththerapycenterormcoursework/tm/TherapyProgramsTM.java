package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TherapyProgramsTM {
    private int ProgramID;
    private String ProgramName;
    private String programDuration;
    private double programFee;
}
