package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DashboardController {

    @FXML
    private JFXButton btnAppointments;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnPatients;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnSetting;

    @FXML
    private JFXButton btnTherapists;

    @FXML
    private JFXButton btnTherapyPrograms;

    @FXML
    private JFXButton btnTherapySessions;

    @FXML
    private JFXButton btnUsers;

    @FXML
    private AnchorPane dashboardLodingPage;

    @FXML
    private AnchorPane pageDashboard;

    private List<JFXButton> dashboardButtons;

    @FXML
    public void initialize() {
        dashboardButtons = Arrays.asList(
                btnAppointments, btnPatients, btnPayment, btnSetting,
                btnTherapists, btnTherapyPrograms, btnTherapySessions, btnUsers
        );
    }

    private void resetButtonStyles() {
        for (JFXButton btn : dashboardButtons) {
            btn.setStyle(""); // Reset to default style
        }
    }

    private void highlightButton(JFXButton selectedButton) {
        resetButtonStyles();
        selectedButton.setStyle("-fx-background-color: #867fbc; -fx-text-fill: white;");
    }

    @FXML
    void btnAppointmentsOnAction(ActionEvent event) {
        highlightButton(btnAppointments);

    }

    @FXML
    void btnPatientsOnAction(ActionEvent event) {
        highlightButton(btnPatients);
        navigateTo("/view/Patients-form.fxml");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        highlightButton(btnPayment);

    }

    @FXML
    void btnSettingOnAction(ActionEvent event) {
        highlightButton(btnSetting);

    }

    @FXML
    void btnTherapistsOnAction(ActionEvent event) {
        highlightButton(btnTherapists);
        navigateTo("/view/Therapists-form.fxml");
    }

    @FXML
    void btnTherapyProgramsOnAction(ActionEvent event) {
        highlightButton(btnTherapyPrograms);
        navigateTo("/view/TherapyPrograms-form.fxml");
    }

    @FXML
    void btnTherapySessionsOnAction(ActionEvent event) {
        highlightButton(btnTherapySessions);

    }

    @FXML
    void btnUsersOnAction(ActionEvent event) {
        highlightButton(btnUsers);
        navigateTo("/view/User-form.fxml");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    private void navigateTo(String fxmlPath) {
        try {
            dashboardLodingPage.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            load.prefWidthProperty().bind(dashboardLodingPage.widthProperty());
            load.prefHeightProperty().bind(dashboardLodingPage.heightProperty());
            dashboardLodingPage.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}
