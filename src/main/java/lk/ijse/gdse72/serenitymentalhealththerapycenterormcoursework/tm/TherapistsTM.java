package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TherapistsTM {
    private int therapistId;
    private String therapistName;
    private String specialization;
    private String email;
    private String therapyHistory;
}
