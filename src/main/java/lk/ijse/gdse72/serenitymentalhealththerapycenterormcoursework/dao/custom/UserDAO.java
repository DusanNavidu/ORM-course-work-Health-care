package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;


import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO extends CrudDAO<User> {

    User findByID(String pk);
    String validateUser(String username, String password);
    Optional<User> findByUserId(String userId);
    boolean updateUsernameAndPassword(String userId, String newUsername, String newPassword);

    String generateNewID() throws Exception;

    User findById(String id);

    boolean ValidUser(String username, String password) throws SQLException;
}
