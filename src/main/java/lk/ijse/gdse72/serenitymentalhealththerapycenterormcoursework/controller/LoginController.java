package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.BOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.UserBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.UserTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label btnFPW;

    @FXML
    private Label btnSignUp;

    @FXML
    private JFXButton btnSignin;

    @FXML
    private ImageView btnViewPW;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private AnchorPane pageLogin;

    private String selectedUserId;
    private final ObservableList<UserTM> userList = FXCollections.observableArrayList();
    private UserBO userBO;

    @FXML
    void btnFPWOnAction(MouseEvent event) {

    }

    @FXML
    void btnSignUpOnAction(MouseEvent event) throws IOException {
        pageLogin.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Register-form.fxml"));
        pageLogin.getChildren().add(load);
    }

    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException {
//        String username = txtUsername.getText();
//        String password = txtPassword.getText();

//        try {
//            boolean isValid = userBO.isValidUser(username, password);
//
//            if (isValid) {
                new Alert(Alert.AlertType.INFORMATION, "Login Success!").showAndWait();
                pageLogin.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard-form.fxml"));
                AnchorPane load = loader.load();
                pageLogin.getChildren().add(load);
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password!").showAndWait();
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    @FXML
    void btnViewPWOnAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);
    }
}