package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TherapyProgramsDTO {
    private int ProgramID;
    private String ProgramName;
    private String programDuration;
    private double programFee;

    public TherapyProgramsDTO(String programName, String programDuration, String programFee) {
        this.ProgramName = programName;
        this.programDuration = programDuration;
        this.programFee = Double.parseDouble(programFee);
    }
}
