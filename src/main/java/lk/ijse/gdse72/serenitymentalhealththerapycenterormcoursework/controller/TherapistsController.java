package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapistBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapistProgramBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapyProgramBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.TherapistBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.TherapistProgramBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.TherapyProgramBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapistDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapistProgramDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.TherapistProgramTM;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.TherapistTM;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.util.NavigationUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TherapistsController implements Initializable {

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnDeleteTP;

    @FXML
    private JFXButton btnEmail;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnResertTP;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSaveTP;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnUpdateTP;

    @FXML
    private JFXButton getProgramIDButton;

    @FXML
    private TableColumn<TherapistProgramTM, String> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private TableColumn<TherapistProgramTM, String> programNameCol;

    @FXML
    private TextField programNameTxt;

    @FXML
    private JFXButton searchButtonTP;

    @FXML
    private TextField searchTxt;

    @FXML
    private TextField searchTxtTP;

    @FXML
    private JFXButton therapistAvailabilityBtn;

    @FXML
    private TableColumn<TherapistTM, String> therapistEmailCol;

    @FXML
    private TextField therapistEmailTxt;

    @FXML
    private TableColumn<TherapistTM, String> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private TableColumn<TherapistTM, String> therapistNameCol;

    @FXML
    private TextField therapistNameTxt;

    @FXML
    private TableColumn<TherapistTM, String> therapistPhoneCol;

    @FXML
    private TextField therapistPhoneTxt;

    @FXML
    private TableView<TherapistProgramTM> therapistProgramTable;

    @FXML
    private TableColumn<TherapistTM, String> therapistSpecsCol;

    @FXML
    private TextField therapistSpecsTxt;

    @FXML
    private TableView<TherapistTM> therapistsTable;

    NavigationUtil navigate = new NavigationUtil();

    private final TherapistBO therapistBO = new TherapistBOImpl();
    TherapistProgramBO therapistProgramBO = new TherapistProgramBOImpl();
    TherapyProgramBO therapyProgramBO = new TherapyProgramBOImpl();

    public void initialize(URL location, ResourceBundle resources) {

        therapistIdCol.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        therapistNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        therapistEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        therapistPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        therapistSpecsCol.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        loadTPTable();

        try {
            refreshPage();
        } catch (Exception e) {
            throw e;
        }
    }

    private void loadTPTable() {
        programIdCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgramId"));
        programNameCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgramName"));
    }

    public void refreshPage() {
        therapistIdTxt.clear();
        therapistNameTxt.clear();
        therapistEmailTxt.clear();
        therapistPhoneTxt.clear();
        therapistSpecsTxt.clear();
        try {
            therapistIdTxt.setText(therapistBO.getNextTherapistPK());
            refreshTable();
        } catch (Exception e) {
            throw e;
        }
    }

    public void refreshTable() {
        ArrayList<TherapistDTO> therapistDtos = therapistBO.getAllTherapists();
        if (therapistDtos != null && !therapistDtos.isEmpty()) {
            ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();
            for (TherapistDTO therapistDto : therapistDtos) {
                TherapistTM therapist = new TherapistTM(
                        therapistDto.getTherapistId(),
                        therapistDto.getName(),
                        therapistDto.getEmail(),
                        therapistDto.getPhone(),
                        therapistDto.getSpecialization()
                );
                therapistTMS.add(therapist);
            }
            therapistsTable.setItems(therapistTMS);
        } else {
            therapistsTable.setItems(FXCollections.emptyObservableList());
        }
    }


    @FXML
    void btnDeleteOnAcion(ActionEvent event) {
        String therapistId = therapistIdTxt.getText();
        if (therapistBO.deleteTherapist(therapistId)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Therapist with ID " + therapistId + " deleted successfully.");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Failed to delete therapist with ID " + therapistId + ".");
        }
    }

    @FXML
    void btnDeleteTPOnAcion(ActionEvent event) {
        String therapistId = therapistIdTxt.getText();
        String programId = programIdTxt.getText();

        boolean isDeleted = therapistProgramBO.deleteTherapistProgram(therapistId, programId);

        if (isDeleted) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Therapist Program deleted successfully.");
            loadTPTableData();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Failed to delete Therapist Program.");
        }
    }

    @FXML
    void btnEmailOnAction(ActionEvent event) {

    }

    @FXML
    void btnResertOnAction(ActionEvent event) {

    }

    @FXML
    void btnResertTPOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        TherapistDTO therapistDto = new TherapistDTO(
                therapistIdTxt.getText(),
                therapistNameTxt.getText(),
                therapistEmailTxt.getText(),
                therapistPhoneTxt.getText(),
                therapistSpecsTxt.getText()
        );

        if (therapistBO.saveTherapist(therapistDto)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Therapist saved successfully.");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Failed to save therapist.");
        }
    }

    @FXML
    void btnSaveTPOnAction(ActionEvent event) {
        String therapistId = therapistIdTxt.getText();
        String programId = programIdTxt.getText();

        boolean isSaved = therapistProgramBO.saveTherapistProgram(therapistId, programId);

        if (isSaved) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Therapist Program saved successfully.");
            loadTPTableData();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Failed to save Therapist Program.");
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String name = searchTxt.getText().trim();

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Please enter a name to search.");
        refreshPage();
            return;
        }

        ArrayList<TherapistDTO> therapistDtos = therapistBO.findByTherapistName(name);

        if (therapistDtos != null && !therapistDtos.isEmpty()) {
            ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();

            for (TherapistDTO therapistDto : therapistDtos) {
                TherapistTM therapistTM = new TherapistTM(
                        therapistDto.getTherapistId(),
                        therapistDto.getName(),
                        therapistDto.getEmail(),
                        therapistDto.getPhone(),
                        therapistDto.getSpecialization()
                );
                therapistTMS.add(therapistTM);
            }

            therapistsTable.setItems(therapistTMS);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "No therapists found with the name " + name + ".");
            therapistsTable.setItems(FXCollections.emptyObservableList());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        TherapistDTO therapistDto = new TherapistDTO(
                therapistIdTxt.getText(),
                therapistNameTxt.getText(),
                therapistEmailTxt.getText(),
                therapistPhoneTxt.getText(),
                therapistSpecsTxt.getText()
        );

        if (therapistBO.updateTherapist(therapistDto)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Therapist updated successfully.");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Failed to update therapist.");
        }
    }

    @FXML
    void btnUpdateTPOnAction(ActionEvent event) {
        String therapistId = programIdTxt.getText();
        String programId = programNameTxt.getText();

        boolean isUpdated = therapistProgramBO.updateTherapistProgram(therapistId, programId);

        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Therapist Program updated successfully.");
            loadTPTableData();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Failed to update Therapist Program.");
        }
    }

    private void loadTPTableData() {
        List<TherapistProgramDTO> programs = therapistProgramBO.getTherapistProgramsByTherapist(therapistIdTxt.getText());
        updateTPTableView(programs);
        programIdTxt.setText("");
        programNameTxt.setText("");
    }

    private void updateTPTableView(List<TherapistProgramDTO> programs) {
        ObservableList<TherapistProgramTM> tableData = FXCollections.observableArrayList();

        for (TherapistProgramDTO program : programs) {
            tableData.add(new TherapistProgramTM(program.getTherapyProgramId(), program.getTherapyProgramName()));
        }

        therapistProgramTable.setItems(tableData);
    }

    private

    @FXML
    void getProgramIDButtonOnAction(ActionEvent event) {
        List<TherapyProgramDTO> selected = therapyProgramBO.findTherapyProgramByName(programNameTxt.getText());

        if (selected != null && !selected.isEmpty()) {
            TherapyProgramDTO firstMatch = selected.get(0);
            programIdTxt.setText(firstMatch.getProgramId());
            programNameTxt.setText(firstMatch.getName());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "No therapy program found with that name.");
        }
    }

    @FXML
    void loadTherapistAvailabilityPage(ActionEvent event) {
        navigate.navigatePopup("/view/therapist-availability-page.fxml", "Manage Therapist Availability");
    }

    @FXML
    void searchButtonTPOnAction(ActionEvent event) {
        String name = searchTxtTP.getText();

        List<TherapistProgramDTO> results = therapistProgramBO.findByProgramName(name);
        updateTPTableView(results);
    }

    @FXML
    void switchSearchButtonFunction(KeyEvent event) {
        if (searchTxt.getText().isEmpty()) {
            btnSearch.setText("Clear");
        } else {
            btnSearch.setText("Search");
        }
    }

    @FXML
    void switchSearchButtonFunctionTP(KeyEvent event) {
        if (searchTxtTP.getText().isEmpty()) {
            searchButtonTP.setText("Clear");
        } else {
            searchButtonTP.setText("Search");
        }
    }

    @FXML
    void tableClick(MouseEvent event) {
        TherapistTM selectedTherapist = therapistsTable.getSelectionModel().getSelectedItem();
        if (selectedTherapist != null) {
            therapistIdTxt.setText(selectedTherapist.getTherapistId());
            therapistNameTxt.setText(selectedTherapist.getName());
            therapistEmailTxt.setText(selectedTherapist.getEmail());
            therapistPhoneTxt.setText(selectedTherapist.getPhone());
            therapistSpecsTxt.setText(selectedTherapist.getSpecialization());
            loadTPTableData();
        }
    }

    @FXML
    void tableClickTP(MouseEvent event) {

    }

}
