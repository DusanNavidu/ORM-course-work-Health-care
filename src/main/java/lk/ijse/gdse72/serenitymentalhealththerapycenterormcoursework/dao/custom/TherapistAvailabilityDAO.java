package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;



import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.TherapistAvailability;

import java.time.LocalDate;
import java.util.List;

public interface TherapistAvailabilityDAO extends CrudDAO<TherapistAvailability> {
    public List<TherapistAvailability> findByTherapistAndDate(String therapistId, LocalDate date);
    public List<TherapistAvailability> findByTherapistId(String therapistId);
    public List<TherapistAvailability> findByDate(LocalDate date);


}
