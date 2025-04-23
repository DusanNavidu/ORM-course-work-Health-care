package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.impl;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.config.SessionFactoryConfig;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.UserDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws Exception {
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
    public boolean update(User entity) throws Exception {
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
    public ArrayList<User> getAll() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<User> users = new ArrayList<>();

        try {
            List<User> patientList = session.createQuery("FROM User ", User.class).list();
            users.addAll(patientList);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public String generateNewID() throws Exception {
        return "";
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public boolean ValidUser(String username, String password) throws SQLException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "FROM User u WHERE u.userName = :username AND u.password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", Integer.parseInt(password)); // if password is int in DB

            User user = query.uniqueResult();

            transaction.commit();
            return user != null;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

}
