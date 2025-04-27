package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.SuperBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {

    boolean saveUser(UserDTO userDTO) throws Exception;

    boolean updateUser(UserDTO userDTO) throws Exception;

    boolean deleteUser(String id) throws Exception;

    List<UserDTO> getAllUsers() throws Exception;

    boolean isValidUser(String username, String password) throws Exception;
}
