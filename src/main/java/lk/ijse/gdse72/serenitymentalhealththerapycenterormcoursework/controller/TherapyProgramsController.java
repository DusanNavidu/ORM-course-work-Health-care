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
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapyProgramsBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramsDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.TherapyProgramsTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapyProgramsController implements Initializable {

    @FXML
    private AnchorPane TherapyProgramsPage;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<TherapyProgramsTM , String> colDuration;

    @FXML
    private TableColumn<TherapyProgramsTM , Double> colFee;

    @FXML
    private TableColumn<TherapyProgramsTM , Integer> colProgramId;

    @FXML
    private TableColumn<TherapyProgramsTM , String> colProgramName;

    @FXML
    private Label lblProgramId;

    @FXML
    private TableView<TherapyProgramsTM> tblTherapyPrograms;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtProgramName;

    private String selectedProgramId;
    private final ObservableList<TherapyProgramsTM> therapyProgramsList = FXCollections.observableArrayList();
    private TherapyProgramsBO therapyProgramsBO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        therapyProgramsBO = (TherapyProgramsBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.THERAPY_PROGRAM);
        setCellValueFactory();
        loadTherapyProgramsData();
        setTableListener();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void loadTherapyProgramsData() {
        therapyProgramsList.clear();
        try {
            List<TherapyProgramsDTO> therapyPrograms = therapyProgramsBO.getAllTherapyPrograms();
            for (TherapyProgramsDTO dto : therapyPrograms) {
                therapyProgramsList.add(new TherapyProgramsTM(
                        dto.getProgramID(),
                        dto.getProgramName(),
                        dto.getProgramDuration(),
                        dto.getProgramFee()
                ));
            }
            tblTherapyPrograms.setItems(therapyProgramsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableListener() {
        tblTherapyPrograms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTherapyProgramsData(newValue);
            }
        });
    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("ProgramID"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("programDuration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
    }

    private void setTherapyProgramsData(TherapyProgramsTM therapyPrograms) {
        selectedProgramId = String.valueOf(therapyPrograms.getProgramID());
        txtProgramName.setText(therapyPrograms.getProgramName());
        txtDuration.setText(therapyPrograms.getProgramDuration());
        txtFee.setText(String.valueOf(therapyPrograms.getProgramFee()));

        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    private void refreshPage(){
        selectedProgramId = null;
        txtProgramName.clear();
        txtDuration.clear();
        txtFee.clear();

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        if (selectedProgramId != null) {
            boolean isDeleted = therapyProgramsBO.deleteTherapyProgram(selectedProgramId);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program deleted successfully!").show();
                loadTherapyProgramsData();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete therapy program!").show();
            }
        }
    }

    @FXML
    void btnResertOnAcion(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            String programName = txtProgramName.getText();
            String programDuration = txtDuration.getText();
            String programFee = txtFee.getText();

            TherapyProgramsDTO therapyProgramsDTO = new TherapyProgramsDTO(programName, programDuration , programFee);

            boolean isSaved = therapyProgramsBO.saveTherapyProgram(therapyProgramsDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program saved successfully!").show();
                refreshPage();
                loadTherapyProgramsData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Therapy Program!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving Therapy Program!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (selectedProgramId == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a Therapy Program to update!").show();
            return;
        }

        try {
            String programName = txtProgramName.getText();
            String programDuration = txtDuration.getText();
            String programFee = txtFee.getText();

            TherapyProgramsDTO therapyProgramsDTO = new TherapyProgramsDTO(programName, programDuration , programFee);

            boolean isUpdated = therapyProgramsBO.updateTherapyProgram(therapyProgramsDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program updated successfully!").show();
                refreshPage();
                loadTherapyProgramsData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Therapy Program!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while updating Therapy Program!").show();
        }
    }

}
