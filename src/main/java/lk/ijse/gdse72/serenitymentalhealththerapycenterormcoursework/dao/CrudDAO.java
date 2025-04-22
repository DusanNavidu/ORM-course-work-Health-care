package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao;

import java.util.ArrayList;

public interface CrudDAO<T, ID> extends SuperDAO {
    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(String id) throws Exception;

    ArrayList<T> getAll() throws Exception;

    String generateNewID() throws Exception;

    T findById(String id);

}
