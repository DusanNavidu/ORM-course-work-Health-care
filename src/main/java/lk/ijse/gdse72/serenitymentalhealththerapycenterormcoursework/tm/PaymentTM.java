package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTM {
    private int paymentId;
    private String date;
    private String time;
    private int programId;
    private int patientId;
    private String amount;
}
