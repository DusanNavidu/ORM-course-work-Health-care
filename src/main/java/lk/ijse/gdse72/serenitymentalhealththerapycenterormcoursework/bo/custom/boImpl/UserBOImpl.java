package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.boImpl;

import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.UserBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.UserDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.UserDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        return userDAO.save(user);
    }
    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        return userDAO.update(user);
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        User user = new User();
        user.setUserId(Integer.parseInt(id));
        return userDAO.delete(String.valueOf(user));
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> users = userDAO.getAll();

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUserName(user.getUserName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhone(user.getPhone());
            userDTO.setPassword(user.getPassword());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean isValidUser(String username, String password) throws Exception {
        return userDAO.ValidUser(username, password);
    }
}
