package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private int paymentId;
    private String date;
    private String time;
    private int programId;
    private int patientId;
    private String amount;

    public PaymentDTO(String date, String time, int programId, int patientId, String amount) {
        this.date = date;
        this.time = time;
        this.programId = programId;
        this.patientId = patientId;
        this.amount = amount;
    }
}
