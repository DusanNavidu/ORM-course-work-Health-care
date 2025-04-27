package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;



import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Payment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PaymentDAO extends CrudDAO<Payment> {

    List<Payment> findByPatientName(String name);
    Optional<Payment> findById(String pk);
    List<Payment> findByDate(LocalDate date);

}
