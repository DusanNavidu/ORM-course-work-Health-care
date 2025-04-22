package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.impl.PatientDAOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.impl.TherapyProgramsDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        USER , THERAPY_PROGRAM , PATIENT
    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
//            case USER:
//                return new UserDAOImpl();
            case THERAPY_PROGRAM:
                return new TherapyProgramsDAOImpl();
            case PATIENT:
                return new PatientDAOImpl();
            default:
                return null;
        }
    }
}
