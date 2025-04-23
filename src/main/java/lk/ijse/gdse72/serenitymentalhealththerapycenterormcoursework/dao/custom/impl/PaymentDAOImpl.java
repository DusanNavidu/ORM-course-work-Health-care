package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.impl;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.config.SessionFactoryConfig;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.PaymentDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public ArrayList<Payment> getAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<Payment> payments = new ArrayList<>();

        try {
            List<Payment> paymentList = session.createQuery("FROM Payment ", Payment.class).list();
            payments.addAll(paymentList);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return payments;
    }

    @Override
    public String generateNewID() throws Exception {
        return "";
    }

    @Override
    public Payment findById(String id) {
        return null;
    }
}
