package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.BOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.UserBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.UserDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.UserTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEmail;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<UserTM , String> colEmail;

    @FXML
    private TableColumn<UserTM , String> colName;

    @FXML
    private TableColumn<UserTM , Integer> colPassword;

    @FXML
    private TableColumn<UserTM , Integer> colPhone;

    @FXML
    private TableColumn<UserTM , Integer> colUserId;

    @FXML
    private Label lblUserId;

    @FXML
    private AnchorPane pageUser;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtUsername;

    private String selectedUserId;
    private final ObservableList<UserTM> userList = FXCollections.observableArrayList();
    private UserBO userBO;

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        if (selectedUserId != null) {
            boolean isDeleted = userBO.deleteUser(selectedUserId);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "User deleted successfully!").show();
                loadUserData();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete user!").show();
            }
        }
    }

    @FXML
    void btnEmailOnAction(ActionEvent event) {

    }

    @FXML
    void btnResertOnAction(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            String name = txtUsername.getText();
            String email = txtEmail.getText();
            int phone = Integer.parseInt(txtPhoneNumber.getText());
            int password = Integer.parseInt(txtPassword.getText());
            int confirmPassword = Integer.parseInt(txtConfirmPassword.getText());

            if (password != confirmPassword) {
                new Alert(Alert.AlertType.WARNING, "Passwords do not match!").show();
                return;
            }

            UserDTO userDTO = new UserDTO(name, email , phone, password);

            boolean isSaved = userBO.saveUser(userDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User saved successfully!").show();
                refreshPage();
                loadUserData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save user!").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Phone number or password must be numeric!").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving user!").show();
        }
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            String name = txtUsername.getText();
            String email = txtEmail.getText();
            int phone = Integer.parseInt(txtPhoneNumber.getText());
            int password = Integer.parseInt(txtPassword.getText());
            int confirmPassword = Integer.parseInt(txtConfirmPassword.getText());

            if (password != confirmPassword) {
                new Alert(Alert.AlertType.WARNING, "Passwords do not match!").show();
                return;
            }

            UserDTO userDTO = new UserDTO(name, email, phone, password);

            boolean isUpdated = userBO.updateUser(userDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "User updated successfully!").show();
                refreshPage();
                loadUserData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update user!").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Phone number or password must be numeric!").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating user!").show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);
        setCellValueFactory();
        loadUserData();
        setTableListener();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void loadUserData() {
        userList.clear();
        try {
            List<UserDTO> users = userBO.getAllUsers();
            for (UserDTO dto : users) {
                userList.add(new UserTM(
                        dto.getUserId(),
                        dto.getUserName(),
                        dto.getEmail(),
                        dto.getPhone(),
                        dto.getPassword()
                ));
            }
            tblUser.setItems(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableListener() {
        tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setUserData(newValue);
            }
        });
    }

    private void setUserData(UserTM user) {
        selectedUserId = String.valueOf(user.getUserId());
        txtUsername.setText(user.getUserName());
        txtEmail.setText(user.getEmail());
        txtPhoneNumber.setText(String.valueOf(user.getPhone()));
        txtPassword.setText(String.valueOf(user.getPassword()));

        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    private void refreshPage() {
        selectedUserId = null;
        txtUsername.clear();
        txtEmail.clear();
        txtPhoneNumber.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }
}