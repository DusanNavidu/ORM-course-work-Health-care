package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RegisterController {

    @FXML
    private Label btnLoginPage;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private ImageView btnVisible1;

    @FXML
    private ImageView btnVisible2;

    @FXML
    private AnchorPane pageSignUp;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginPageOnAction(MouseEvent event) throws IOException {
        pageSignUp.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Login-form.fxml"));
        pageSignUp.getChildren().add(load);
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {

    }

    @FXML
    void btnVisible1OnClicked(MouseEvent event) {

    }

    @FXML
    void btnVisible2OnClicked(MouseEvent event) {

    }

}
