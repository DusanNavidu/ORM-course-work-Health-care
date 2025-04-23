package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PaymentDTO;

import java.util.List;

public interface PaymentBO {
    boolean savePayment(PaymentDTO paymentDTO) throws Exception;

    List<PaymentDTO> getAllPayments() throws Exception;
}
