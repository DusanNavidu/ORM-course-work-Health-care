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
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.PatientBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.PatientTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PatientsController implements Initializable {

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
    private AnchorPane pagePatient;

    @FXML
    private TableColumn<PatientTM , String> patientAddressCol;

    @FXML
    private TextField patientAddressTxt;

    @FXML
    private TableColumn<PatientTM , String> patientEmailCol;

    @FXML
    private TextField patientEmailTxt;

    @FXML
    private TableColumn<PatientTM , String> patientHistoryCol;

    @FXML
    private TextArea patientHistoryTxt;

    @FXML
    private TableColumn<PatientTM , String> patientIdCol;

    @FXML
    private Label patientIdTxt;

    @FXML
    private TableColumn<PatientTM , String> patientNameCol;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private TableColumn<PatientTM , String> patientPhoneCol;

    @FXML
    private TextField patientPhoneTxt;

    @FXML
    private TableView<PatientTM> patientsTable;

    @FXML
    private TextField searchTxt;

    private final PatientBOImpl patientBO = new PatientBOImpl();

    public void initialize(URL location, ResourceBundle resources){

        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        patientNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        patientEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        patientPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        patientAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        patientHistoryCol.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
        try {
            refreshPage();
        }catch (Exception e) {
            throw e;
        }

    }

    public void refreshPage(){

        String nextPatientId = patientBO.getNextPatientPK();
        patientIdTxt.setText(nextPatientId);

        patientNameTxt.clear();
        patientEmailTxt.clear();
        patientPhoneTxt.clear();
        patientAddressTxt.clear();
        patientHistoryTxt.clear();
        try {
            patientIdTxt.setText(patientBO.getNextPatientPK());
            refreshTable();
        } catch (Exception e) {
            throw e;
        }
    }

    public void refreshTable(){
        ArrayList<PatientDTO> patientDtos = patientBO.getAllPatients();
        if (patientDtos != null && !patientDtos.isEmpty()) {
            ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();
            for(PatientDTO patientDto : patientDtos){
                PatientTM patient = new PatientTM(
                        patientDto.getPatientId(),
                        patientDto.getName(),
                        patientDto.getEmail(),
                        patientDto.getPhone(),
                        patientDto.getAddress(),
                        patientDto.getMedicalHistory()
                );
                patientTMS.add(patient);
            }
            patientsTable.setItems(patientTMS);
        } else {
            patientsTable.setItems(FXCollections.emptyObservableList());
        }
    }

    @FXML
    void btnDeleteOnAcion(ActionEvent event) {
        String patientId = patientIdTxt.getText();
        if (patientBO.deletePatient(patientId)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient deleted successfully!");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete patient!");
            alert.showAndWait();
        }
    }

    @FXML
    void btnEmailOnAction(ActionEvent event) {

    }

    @FXML
    void btnResertOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        PatientDTO patientDto = new PatientDTO(
                patientIdTxt.getText(),
                patientNameTxt.getText(),
                patientEmailTxt.getText(),
                patientPhoneTxt.getText(),
                patientAddressTxt.getText(),
                patientHistoryTxt.getText()
        );

        if (patientBO.savePatient(patientDto)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient saved successfully!");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save patient!");
            alert.showAndWait();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String name = searchTxt.getText().trim();

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a name to search");
            refreshPage();
            return;
        }

        ArrayList<PatientDTO> patientDtos = patientBO.findByPatientName(name);

        if (patientDtos != null && !patientDtos.isEmpty()) {
            ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

            for (PatientDTO patientDto : patientDtos) {
                PatientTM patientTM = new PatientTM(
                        patientDto.getPatientId(),
                        patientDto.getName(),
                        patientDto.getEmail(),
                        patientDto.getPhone(),
                        patientDto.getAddress(),
                        patientDto.getMedicalHistory()
                );
                patientTMS.add(patientTM);
            }

            patientsTable.setItems(patientTMS);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No patients found with the given name");
            patientsTable.setItems(FXCollections.emptyObservableList());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        PatientDTO patientDto = new PatientDTO(
                patientIdTxt.getText(),
                patientNameTxt.getText(),
                patientEmailTxt.getText(),
                patientPhoneTxt.getText(),
                patientAddressTxt.getText(),
                patientHistoryTxt.getText()
        );

        if (patientBO.updatePatient(patientDto)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient updated successfully!");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update patient!");
            alert.showAndWait();
        }
    }

    @FXML
    void tableClick(MouseEvent event) {
        PatientTM selectedPatient = patientsTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            patientIdTxt.setText(selectedPatient.getPatientId());
            patientNameTxt.setText(selectedPatient.getName());
            patientEmailTxt.setText(selectedPatient.getEmail());
            patientPhoneTxt.setText(selectedPatient.getPhone());
            patientAddressTxt.setText(selectedPatient.getAddress());
            patientHistoryTxt.setText(selectedPatient.getMedicalHistory());
        }
    }

}
