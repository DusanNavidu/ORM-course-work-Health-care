package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapyProgram;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapySession;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PaymentDTO {
    private String paymentId;
    private Patient patient;
    private TherapyProgram therapyProgram;
    private TherapySession therapySession;
    private BigDecimal amount;
    private LocalDate paymentDate;
}