package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl;




import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.UserBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.DAOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.UserDAO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.UserDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity.User;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.util.KeepUserIDUtil;
import org.springframework.security.crypto.bcrypt.BCrypt;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);


    @Override
    public String validateUser(String username, String password) {
        Optional<User> optionalUser = userDAO.findByName(username);

        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();

        if (verifyPassword(password, user.getPassword())) {
            KeepUserIDUtil.getInstance().setCurrentUserId(user.getUser_id());
            return user.getRole();
        } else {
            return null;
        }
    }


    public String hashPassword(String password){
            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(password,salt);
            return hashedPassword;
    }

    public boolean verifyPassword(String plainPassword, String hashedPassword) {
            if (hashedPassword == null || hashedPassword.isEmpty()) {
                throw new IllegalArgumentException("Hashed password cannot be null or empty");
            }

            try {
                return BCrypt.checkpw(plainPassword, hashedPassword);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid password hash format: " + hashedPassword);
                return false;
            }
    }

    public boolean registerUser(UserDTO dto){
        Optional<User> optionalUser = userDAO.findByName(dto.getUsername());
        if (optionalUser.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUser_id(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPassword(hashPassword(dto.getPassword()));

        return userDAO.save(user);
    }

    // --------------------------------------------


    @Override
    public boolean updateUser(UserDTO dto) {
        Optional<User> optionalUser = userDAO.findByName(dto.getUsername());
        if (optionalUser.isEmpty()) {
            return false;
        }

        User existingUser = optionalUser.get();

        User user = new User();
        user.setUser_id(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(existingUser.getRole());
        user.setPassword(hashPassword(dto.getPassword()));

        return userDAO.update(user);
    }


    @Override
    public boolean deleteUser(String userId) {
        return userDAO.delete(userId);
    }

    @Override
    public ArrayList<UserDTO> searchUser(String name) {
        Optional<User> optionalUser = userDAO.findByName(name);
        ArrayList<UserDTO> userDtos = new ArrayList<>();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDto = new UserDTO();
            userDto.setUserId(user.getUser_id());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setRole(user.getRole());
            userDtos.add(userDto);
        }

        return userDtos;
    }


    @Override
    public String generateNextUserId() {
        Optional<String> lastPkOpt = userDAO.getLastPK();

        if (lastPkOpt.isPresent()) {
            String lastPk = lastPkOpt.get(); // e.g., U005
            int num = Integer.parseInt(lastPk.replace("U", ""));
            return String.format("U%03d", num + 1);
        } else {
            return "U001";
        }
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() {
        List<User> users = userDAO.getAll();

        ArrayList<UserDTO> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDTO userDto = new UserDTO();
            userDto.setUserId(user.getUser_id());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setRole(user.getRole());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDTO findUserByUserId(String userId) {
        Optional<User> optionalUser = userDAO.findByUserId(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDto = new UserDTO();
            userDto.setUserId(user.getUser_id());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setRole(user.getRole());
            return userDto;
        }

        return null; // Return null if the user isn't found
    }


    @Override
    public boolean updateUsernameAndPassword(String userId, String newUsername, String newPassword) {
        String hashedPassword = hashPassword(newPassword);
        return userDAO.updateUsernameAndPassword(userId, newUsername, hashedPassword);
    }




}
