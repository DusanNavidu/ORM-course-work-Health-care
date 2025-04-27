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
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.PatientProgramBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapyProgramBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.PatientProgramBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.TherapyProgramBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientProgramDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.PatientProgramTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PatientProgramController implements Initializable {

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
    private TableColumn<PatientProgramTM, Double> leftToPayCol;

    @FXML
    private Label leftToPayTxt;

    @FXML
    private TableColumn<PatientProgramTM, String> patientIdCol;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TableColumn<PatientProgramTM, String> patientNameCol;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private TableView<PatientProgramTM> patientProgramTable;

    @FXML
    private JFXButton patientSearchButton;

    @FXML
    private TableColumn<PatientProgramTM, String> paymentIdCol;

    @FXML
    private TextField paymentIdTxt;

    @FXML
    private TableColumn<PatientProgramTM, Double> programFeeCol;

    @FXML
    private Label programFeeTxt;

    @FXML
    private TableColumn<PatientProgramTM, String> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private TextField programNameTxt;

    @FXML
    private JFXButton programSearchBtn;

    @FXML
    private TableColumn<PatientProgramTM, String> registerDateCol;

    @FXML
    private DatePicker registerDateTxt;

    @FXML
    private JFXButton searchToggleButton;

    @FXML
    private TextField searchTxt;

    private final PatientProgramBO programBO = new PatientProgramBOImpl();
    private final TherapyProgramBO therapyProgramBO = new TherapyProgramBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        programIdCol.setCellValueFactory(new PropertyValueFactory<>("programId"));
        registerDateCol.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        paymentIdCol.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        programFeeCol.setCellValueFactory(new PropertyValueFactory<>("programFee"));
        leftToPayCol.setCellValueFactory(new PropertyValueFactory<>("leftToPay"));
        refreshPage();
    }

    private void refreshPage() {
        refreshTable();

        patientIdTxt.clear();
        patientNameTxt.clear();
        programIdTxt.clear();
        programNameTxt.clear();
        paymentIdTxt.clear();
        registerDateTxt.setValue(null);
        programFeeTxt.setText("");
        leftToPayTxt.setText("");
    }

    private void refreshTable() {
        ArrayList<PatientProgramDTO> programList = programBO.getAllPatientPrograms();
        ObservableList<PatientProgramTM> programTMS = FXCollections.observableArrayList();

        for (PatientProgramDTO dto : programList) {
            TherapyProgramDTO program = therapyProgramBO.findTherapyProgramByID(dto.getProgramId());
            programTMS.add(new PatientProgramTM(
                    dto.getPatientId(),
                    dto.getPatientName(),
                    dto.getProgramId(),
                    dto.getProgramName(),
                    dto.getRegistrationDate(),
                    dto.getPaymentId(),
                    program.getFee(),
                    dto.getLeftToPay()
            ));
        }

        patientProgramTable.setItems(programTMS);
    }

    @FXML
    void btnDeleteOnAcion(ActionEvent event) {
        String patientName = patientNameTxt.getText();
        String programName = programNameTxt.getText();

        if (programBO.deletePatientProgram(patientName, programName)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient program deleted successfully");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete patient program");
        }
    }

    @FXML
    void btnEmailOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Button is implemented yet");
    }

    @FXML
    void btnResertOnAction(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        PatientProgramDTO dto = new PatientProgramDTO(
                patientIdTxt.getText(),
                patientNameTxt.getText(),
                programIdTxt.getText(),
                programNameTxt.getText(),
                registerDateTxt.getValue(),
                paymentIdTxt.getText(),
                null
        );

        if (programBO.savePatientProgram(dto)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient program saved successfully");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save patient program");
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String query = searchTxt.getText().trim();

        if (query.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a search term");
            refreshPage();
            return;
        }

        ObservableList<PatientProgramTM> programTMS = FXCollections.observableArrayList();

        if (searchToggleButton.isPressed()) {
            ArrayList<PatientProgramDTO> programList = programBO.search(query , false);
            for (PatientProgramDTO dto : programList) {
                TherapyProgramDTO program = therapyProgramBO.findTherapyProgramByID(dto.getProgramId());
                programTMS.add(new PatientProgramTM(
                        dto.getPatientId(),
                        dto.getPatientName(),
                        dto.getProgramId(),
                        dto.getProgramName(),
                        dto.getRegistrationDate(),
                        dto.getPaymentId(),
                        program.getFee(),
                        dto.getLeftToPay()
                ));
            }
        } else {
            ArrayList<PatientProgramDTO> programList = programBO.search(query, true);
            for (PatientProgramDTO dto : programList) {
                TherapyProgramDTO program = therapyProgramBO.findTherapyProgramByID(dto.getProgramId());
                programTMS.add(new PatientProgramTM(
                        dto.getPatientId(),
                        dto.getPatientName(),
                        dto.getProgramId(),
                        dto.getProgramName(),
                        dto.getRegistrationDate(),
                        dto.getPaymentId(),
                        program.getFee(),
                        dto.getLeftToPay()
                ));
            }
        }

        if (programTMS.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No results found for: " + query);
        }

        patientProgramTable.setItems(programTMS);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        PatientProgramDTO dto = new PatientProgramDTO(
                patientIdTxt.getText(),
                patientNameTxt.getText(),
                programIdTxt.getText(),
                programNameTxt.getText(),
                registerDateTxt.getValue(),
                paymentIdTxt.getText(),
                null
        );

        if (programBO.updatePatientProgram(dto)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient program updated successfully");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update patient program");
        }
    }

    @FXML
    void programSearchBtnOnAction(ActionEvent event) {
        String name = programNameTxt.getText().trim();
        TherapyProgramDTO program = programBO.findByProgramName(name);
        if (program == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No program found with the name: " + name);
            return;
        }
        programIdTxt.setText(program.getProgramId());
        programNameTxt.setText(program.getName());
    }

    @FXML
    void searchPatientOnAction(ActionEvent event) {
        String name = patientNameTxt.getText().trim();
        PatientDTO patient = programBO.findByPatientName(name);
        if (patient == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No patient found with the name: " + name);
        return;
        }
        patientIdTxt.setText(patient.getPatientId());
        patientNameTxt.setText(patient.getName());
    }

    @FXML
    void searchToggleButtonOnAction(ActionEvent event) {
        if (searchToggleButton.isPressed()) {
            searchToggleButton.setText("Search by Program");
        } else {
            searchToggleButton.setText("Search by Patient");
        }
    }

    @FXML
    void tableClick(MouseEvent event) {

    }

}
