package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.boImpl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOType {
        USER , THERAPY_PROGRAM , PATIENT , PAYMENT , THERAPIST , APPOINTMENT , THERAPY_SESSION
    }

    public <T> T getBO(BOType boType) {
        switch (boType) {
//            case USER:
//                return (T) new UserBOImpl();
            case THERAPY_PROGRAM:
                return (T) new TherapyProgramsBOImpl();
            case PATIENT:
                return (T) new PatientsBOImpl();
            case USER:
                return (T) new UserBOImpl();
            default:
                return null;
        }
    }
}
