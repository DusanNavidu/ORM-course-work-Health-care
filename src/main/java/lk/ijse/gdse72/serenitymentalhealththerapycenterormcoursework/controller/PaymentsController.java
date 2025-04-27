package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.PatientBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.PaymentBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapyProgramBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.PatientBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.PaymentBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.TherapyProgramBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PaymentDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.PaymentTM;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentsController implements Initializable {

    @FXML
    private TableColumn<PaymentTM, Double> amountCol;

    @FXML
    private TextField amountTxt;

    @FXML
    private AnchorPane bodyPane;

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
    private TableColumn<PaymentTM, LocalDate> dateCol;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private TableColumn<PaymentTM, String> patientIdCol;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private JFXButton patientSearchButton;

    @FXML
    private TableColumn<PaymentTM, String> paymentIdCol;

    @FXML
    private Label paymentIdTxt;

    @FXML
    private ChoiceBox<String> paymentTypeChoice;

    @FXML
    private TableView<PaymentTM> paymentsTable;

    @FXML
    private TableColumn<PaymentTM, String> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private TextField programNameTxt;

    @FXML
    private JFXButton programSearchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<PaymentTM, String> sessionIdCol;

    @FXML
    private HBox sessionIdPart;

    @FXML
    private TextField sessionIdTxt;

    private boolean fromMainPage = false;

    public void setFromMainPage(boolean fromMainPage) {
        this.fromMainPage = fromMainPage;
    }

    public void configurePage() {
        if (fromMainPage) {
            btnSearch.setVisible(false);
            btnDelete.setVisible(false);
            btnUpdate.setVisible(false);
        }
    }

    private final PatientBO patientBO = new PatientBOImpl();
    private final TherapyProgramBO programBO = new TherapyProgramBOImpl();
    PaymentBO paymentBO = new PaymentBOImpl();

    public void initialize(URL location, ResourceBundle resources) {
        paymentIdCol.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        programIdCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgramId"));
        sessionIdCol.setCellValueFactory(new PropertyValueFactory<>("therapySessionId"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        refreshPage();

        paymentTypeChoice.setItems(FXCollections.observableArrayList("Program Register", "Session Payment"));
        paymentTypeChoice.setValue("Program Register");
        sessionIdPart.setVisible(false);
        paymentTypeChoice.setOnAction(e -> toggleSessionField());
    }

    private void toggleSessionField() {
        sessionIdPart.setVisible(!"Program Register".equals(paymentTypeChoice.getValue()));
    }

    private void refreshTable() {
        ArrayList<PaymentDTO> paymentList = paymentBO.getAllPayments();
        ObservableList<PaymentTM> payments = FXCollections.observableArrayList();

        for (PaymentDTO dto : paymentList) {
            String session = null;
            if (dto.getTherapySession() != null) {
                session = dto.getTherapySession().getSession_id();
            }
            payments.add(new PaymentTM(
                    dto.getPaymentId(),
                    dto.getPatient().getPatient_id(),
                    dto.getTherapyProgram().getProgram_id(),
                    session,
                    dto.getAmount(),
                    dto.getPaymentDate()
            ));
        }

        paymentsTable.setItems(payments);
    }

    private void refreshPage() {
        refreshTable();

        String nextPaymentId = paymentBO.getNextPaymentPK();
        paymentIdTxt.setText(nextPaymentId);
        patientIdTxt.clear();
        patientNameTxt.clear();
        programIdTxt.clear();
        programNameTxt.clear();
        sessionIdTxt.clear();
        amountTxt.clear();
        dateTxt.setValue(null);
    }


    @FXML
    void btnDeleteOnAcion(ActionEvent event) {
        String id = paymentIdTxt.getText();
        if (paymentBO.deletePayment(id)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment deleted successfully");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete payment");
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
        PaymentDTO dto = paymentBO.constructPaymentDto(
                paymentIdTxt.getText(),
                patientIdTxt.getText(),
                programIdTxt.getText(),
                sessionIdTxt.getText(),
                new BigDecimal(amountTxt.getText()),
                dateTxt.getValue()
        );

        if (paymentBO.savePayment(dto)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment saved successfully");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save payment");
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String query = searchTxt.getText();
        if (query.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please enter a search query");
            refreshPage();
            return;
        }
        ArrayList<PaymentDTO> results = paymentBO.searchByPatientName(query);

        ObservableList<PaymentTM> payments = FXCollections.observableArrayList();

        for (PaymentDTO dto : results) {
            String sessionId = null;
            if (dto.getTherapySession() != null) {
                sessionId = dto.getTherapySession().getSession_id();
            }

            payments.add(new PaymentTM(
                    true,
                    dto.getPaymentId(),
                    dto.getPatient().getPatient_id(),
                    dto.getTherapyProgram().getProgram_id(),
                    sessionId,
                    dto.getAmount(),
                    dto.getPaymentDate()
            ));
        }
        paymentsTable.setItems(payments);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        PaymentDTO dto = paymentBO.constructPaymentDto(
                paymentIdTxt.getText(),
                patientIdTxt.getText(),
                programIdTxt.getText(),
                sessionIdTxt.getText(),
                new BigDecimal(amountTxt.getText()),
                dateTxt.getValue()
        );

        if (paymentBO.updatePayment(dto)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment updated successfully");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update payment");
        }
    }

    @FXML
    void searchPatientOnAction(ActionEvent event) {
        String name = patientNameTxt.getText().trim();
        ArrayList<PatientDTO> patients = patientBO.findByPatientName(name);

        if (patients.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No patients found with the name: " + name);
            return;
        }

        PatientDTO patient = patients.getFirst();

        patientIdTxt.setText(patient.getPatientId());
        patientNameTxt.setText(patient.getName());
    }

    @FXML
    void searchProgramOnAction(ActionEvent event) {
        String name = programNameTxt.getText().trim();
        ArrayList<TherapyProgramDTO> programs = programBO.findTherapyProgramByName(name);

        if (programs.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No programs found with the name: " + name);
            return;
        }

        TherapyProgramDTO program = programs.getFirst();

        programIdTxt.setText(program.getProgramId());
        programNameTxt.setText(program.getName());
    }

    @FXML
    void tableClick(MouseEvent event) {
        PaymentTM selected = paymentsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            paymentIdTxt.setText(selected.getPaymentId());
            patientIdTxt.setText(selected.getPatientId());
            patientNameTxt.setText(patientBO.findPatientByID(selected.getPatientId()).getName());
            programIdTxt.setText(selected.getTherapyProgramId());
            programNameTxt.setText(programBO.findTherapyProgramByID(selected.getTherapyProgramId()).getName());
            sessionIdTxt.setText(selected.getTherapySessionId());
            amountTxt.setText(String.valueOf(selected.getAmount()));
            dateTxt.setValue(selected.getPaymentDate());
        }
    }
}
