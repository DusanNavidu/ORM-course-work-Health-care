package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TherapistProgramTM {
    private String therapistId;
    private String therapyProgramId;
    private String therapyProgramName;

    public TherapistProgramTM(String therapyProgramId, String therapyProgramName) {
        this.therapyProgramId = therapyProgramId;
        this.therapyProgramName = therapyProgramName;
    }

}
