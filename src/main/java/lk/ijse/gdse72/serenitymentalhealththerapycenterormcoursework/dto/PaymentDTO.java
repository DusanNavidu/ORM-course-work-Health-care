package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private int paymentId;
    private Date date;
    private Date time; // keep this as Date to match the entity
    private int programId;
    private int patientId;
    private String amount;

    public PaymentDTO(Date date, Date time, int programId, int patientId, String amount) {
        this.date = date;
        this.time = time;
        this.programId = programId;
        this.patientId = patientId;
        this.amount = amount;
    }
}
