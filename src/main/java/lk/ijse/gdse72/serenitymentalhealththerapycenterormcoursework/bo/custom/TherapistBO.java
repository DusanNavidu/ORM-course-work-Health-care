package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapistDTO;

import java.util.ArrayList;

public interface TherapistBO  extends SuperBO {
    public boolean saveTherapist(TherapistDTO dto);
    public boolean updateTherapist(TherapistDTO dto);
    public boolean deleteTherapist(String pk);
    public ArrayList<TherapistDTO> getAllTherapists();
    public ArrayList<TherapistDTO> findByTherapistName(String name);
    public TherapistDTO findByTherapistId(String id);
    public String getNextTherapistPK();
}
