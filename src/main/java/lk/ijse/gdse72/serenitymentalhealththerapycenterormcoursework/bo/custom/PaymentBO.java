package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PaymentDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;


public interface PaymentBO extends SuperBO {

    public boolean savePayment(PaymentDTO dto);
    public boolean updatePayment(PaymentDTO dto);
    public boolean deletePayment(String paymentId);
    public ArrayList<PaymentDTO> getAllPayments();
    public ArrayList<PaymentDTO> searchByPatientName(String name);
    public ArrayList<PaymentDTO> searchByDate(LocalDate date);
    public String getNextPaymentPK();


    public PaymentDTO constructPaymentDto(String paymentId, String patientId, String programId, String sessionId, BigDecimal amount, LocalDate date);

}
