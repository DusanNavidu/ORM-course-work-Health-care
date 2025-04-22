package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.impl;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.config.SessionFactoryConfig;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.PatientDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public boolean save(Patient entity) throws Exception {
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
    public boolean update(Patient entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(entity);
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
    public boolean delete(String id) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(id);
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
    public ArrayList<Patient> getAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<Patient> patients = new ArrayList<>();

        try {
            List<Patient> patientList = session.createQuery("FROM Patient", Patient.class).list();
            patients.addAll(patientList);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return patients;
    }

    @Override
    public String generateNewID() throws Exception {
        return "";
    }

    @Override
    public Patient findById(String id) {
        return null;
    }
}
