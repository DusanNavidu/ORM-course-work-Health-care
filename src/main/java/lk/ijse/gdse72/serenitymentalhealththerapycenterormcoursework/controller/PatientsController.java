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
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.PatientsBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PatientDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.PatientTM;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

public class PatientsController implements Initializable {

    @FXML private AnchorPane PatientsPage;
    @FXML private JFXButton btnDelete, btnEmail, btnResert, btnSave, btnSearch, btnUpdate;
    @FXML private TableColumn<PatientTM, String> colDOB, colMedicalHistory, colPatientsEmail, colPatientsName, colTherapyHistory;
    @FXML private TableColumn<PatientTM, Integer> colPatientsID;
    @FXML private Label lblID;
    @FXML private DatePicker selDOB;
    @FXML private TableView<PatientTM> tbtPatients;
    @FXML private TextField txtEmail, txtMedicalHistory, txtName, txtTherapyHistory;

    private String selectedPatientId;
    private final ObservableList<PatientTM> patientList = FXCollections.observableArrayList();
    private PatientsBO patientsBO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientsBO = (PatientsBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.PATIENT);
        setCellValueFactory();
        loadPatientData();
        setTableListener();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void setCellValueFactory() {
        colPatientsID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colPatientsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPatientsEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colTherapyHistory.setCellValueFactory(new PropertyValueFactory<>("therapyHistory"));
        colMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("medicalHistory"));
    }

    private void loadPatientData() {
        patientList.clear();
        try {
            List<PatientDTO> patients = patientsBO.getAllPatients();
            for (PatientDTO dto : patients) {
                patientList.add(new PatientTM(
                        dto.getPatientId(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getDateOfBirth(),
                        dto.getTherapyHistory(),
                        dto.getMedicalHistory()
                ));
            }
            tbtPatients.setItems(patientList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableListener() {
        tbtPatients.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setPatientData(newValue);
            }
        });
    }

    private void setPatientData(PatientTM patient) {
        selectedPatientId = String.valueOf(patient.getPatientId());
        txtName.setText(patient.getName());

        try {
            if (patient.getDateOfBirth() != null && !patient.getDateOfBirth().isEmpty()) {
                selDOB.setValue(LocalDate.parse(patient.getDateOfBirth()));
            } else {
                selDOB.setValue(null);
            }
        } catch (DateTimeParseException e) {
            selDOB.setValue(null);
            System.err.println("Invalid date format: " + patient.getDateOfBirth());
        }

        txtEmail.setText(patient.getEmail());
        txtTherapyHistory.setText(patient.getTherapyHistory());
        txtMedicalHistory.setText(patient.getMedicalHistory());

        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    private void refreshPage() {
        selectedPatientId = null;
        txtName.clear();
        txtEmail.clear();
        selDOB.setValue(null);
        txtTherapyHistory.clear();
        txtMedicalHistory.clear();
        tbtPatients.getSelectionModel().clearSelection();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    @FXML
    void btnDeleteOnAcion(ActionEvent event) throws Exception {
        if (selectedPatientId != null) {
            boolean isDeleted = patientsBO.deletePatient(selectedPatientId);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Patient deleted successfully!").show();
                loadPatientData();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete patient!").show();
            }
        }
    }

    @FXML
    void btnResertOnAction(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        try {
            String name = txtName.getText();
            String dateOfBirth = selDOB.getValue().toString();
            String email = txtEmail.getText();
            String therapyHistory = txtTherapyHistory.getText();
            String medicalHistory = txtMedicalHistory.getText();

            PatientDTO patientDTO = new PatientDTO(name, dateOfBirth , email, therapyHistory, medicalHistory);

            boolean isSaved = patientsBO.savePatient(patientDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Patient saved successfully!").show();
                refreshPage();
                loadPatientData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save patient!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving patient!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        if (selectedPatientId == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a patient to update!").show();
            return;
        }

        try {
            String name = txtName.getText();
            String dateOfBirth = selDOB.getValue().toString();
            String email = txtEmail.getText();
            String therapyHistory = txtTherapyHistory.getText();
            String medicalHistory = txtMedicalHistory.getText();

            PatientDTO patientDTO = new PatientDTO(Integer.parseInt(selectedPatientId), name, dateOfBirth ,email, therapyHistory, medicalHistory);

            boolean isUpdated = patientsBO.updatePatient(patientDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Patient updated successfully!").show();
                refreshPage();
                loadPatientData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update patient!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating patient!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        // Search functionality implementation (optional)
    }

    @FXML
    void btnEmailOnAction(ActionEvent event) {
        // Email functionality implementation (optional)
    }
}
