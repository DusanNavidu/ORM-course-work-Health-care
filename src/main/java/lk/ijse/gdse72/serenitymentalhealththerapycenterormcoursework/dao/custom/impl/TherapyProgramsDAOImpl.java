package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.impl;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.config.SessionFactoryConfig;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.TherapyProgramsDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapyPrograms;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TherapyProgramsDAOImpl implements TherapyProgramsDAO {
    @Override
    public boolean save(TherapyPrograms entity) throws Exception {
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
    public boolean update(TherapyPrograms entity) throws Exception {
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
    public ArrayList<TherapyPrograms> getAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<TherapyPrograms> patients = new ArrayList<>();

        try {
            List<TherapyPrograms> patientList = session.createQuery("FROM TherapyPrograms", TherapyPrograms.class).list();
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
    public TherapyPrograms findById(String id) {
        return null;
    }
}
