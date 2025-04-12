package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

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
        pageLogin.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Dashboard-form.fxml"));
        pageLogin.getChildren().add(load);
    }

    @FXML
    void btnViewPWOnAction(MouseEvent event) {

    }
}
