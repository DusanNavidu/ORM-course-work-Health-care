package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.UserDTO;

import java.util.ArrayList;

public interface UserBO extends SuperBO {

    public String validateUser(String username, String password);
    public boolean registerUser(UserDTO dto);
    public boolean updateUser(UserDTO dto);
    public boolean deleteUser(String userId);
    public ArrayList<UserDTO> searchUser(String userId);
    public String generateNextUserId();
    public ArrayList<UserDTO> getAllUsers();
    public UserDTO findUserByUserId(String userId);
    public boolean updateUsernameAndPassword(String userId, String newUsername, String newPassword);


}
