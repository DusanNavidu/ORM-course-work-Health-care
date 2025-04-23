package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.CrudDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User, String> {

    boolean ValidUser(String username, String password) throws SQLException;
}
