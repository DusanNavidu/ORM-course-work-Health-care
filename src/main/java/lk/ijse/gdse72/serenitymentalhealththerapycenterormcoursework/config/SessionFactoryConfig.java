package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.config;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Patient;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.Payment;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapyPrograms;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;

    private final SessionFactory sessionFactory;

    public SessionFactoryConfig(){
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Payment.class)
//                    .addAnnotatedClass(Appointment.class)
//                    .addAnnotatedClass(Therapist.class)
                    .addAnnotatedClass(TherapyPrograms.class);
//                    .addAnnotatedClass(TherapySession.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to build SessionFactory.", e);
        }
    }

    public static SessionFactoryConfig getInstance(){
        return (factoryConfig == null) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
