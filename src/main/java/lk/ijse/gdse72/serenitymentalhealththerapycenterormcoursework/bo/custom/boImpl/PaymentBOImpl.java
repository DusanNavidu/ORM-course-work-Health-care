package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.boImpl;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.PaymentBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.PatientDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.PaymentDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapyProgramsDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PaymentDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Payment;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapyPrograms;

import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
        return false;
    }

    @Override
    public List<PaymentDTO> getAllPayments() throws Exception {
        return List.of();
    }

//    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);
//    PatientDAO patientDAO = (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PATIENT);
//    TherapyProgramsDAO therapyProgramsDAO = (TherapyProgramsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.THERAPY_PROGRAM);
//    @Override
//    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
//        Payment payment = new Payment();
//
//        payment.setPaymentDate(paymentDTO.getDate());
//        payment.setPaymentTime(paymentDTO.getTime());
//
//        payment.setAmount(paymentDTO.getAmount());
//
//        Patient patient = patientDAO.findById(String.valueOf(paymentDTO.getPatientId()));
//        if (patient == null) throw new RuntimeException("Patient not found");
//        payment.setPatient(patient);
//
//        TherapyPrograms program = therapyProgramsDAO.findById(String.valueOf(paymentDTO.getProgramId()));
//        if (program == null) throw new RuntimeException("Program not found");
//        payment.setProgram(program);
//
//        return paymentDAO.save(payment);
//    }
//
//    @Override
//    public List<PaymentDTO> getAllPayments() throws Exception {
//        return paymentDAO.getAll().stream().map(payment -> new PaymentDTO(
//                payment.getPaymentId(),
//                payment.getPaymentDate(),
//                payment.getPaymentTime(),
//                payment.getProgram().getProgramID(),
//                payment.getPatient().getPatientId(),
//                payment.getAmount()
//        )).toList();
//    }
}
