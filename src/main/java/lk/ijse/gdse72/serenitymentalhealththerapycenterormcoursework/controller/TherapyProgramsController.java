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
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapyProgramBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.impl.TherapyProgramBOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapyProgramDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.TherapyProgramTM;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapyProgramsController implements Initializable {

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
    private TextArea descriptionTxt;

    @FXML
    private TextField durationTxt;

    @FXML
    private TextField feeTxt;

    @FXML
    private TableColumn<TherapyProgramTM, String> programDescriptionCol;

    @FXML
    private TableColumn<TherapyProgramTM, String> programDurationCol;

    @FXML
    private TableColumn<TherapyProgramTM, BigDecimal> programFeeCol;

    @FXML
    private TableColumn<TherapyProgramTM, String> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private TableColumn<TherapyProgramTM, String> programNameCol;

    @FXML
    private TextField programNameTxt;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableView<TherapyProgramTM> therapyProgramsTable;

    private final TherapyProgramBO therapyProgramBO = new TherapyProgramBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        programIdCol.setCellValueFactory(new PropertyValueFactory<>("programId"));
        programNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        programDurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        programFeeCol.setCellValueFactory(new PropertyValueFactory<>("fee"));
        programDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        refreshPage();
    }

    public void refreshPage() {
        programIdTxt.clear();
        programNameTxt.clear();
        durationTxt.clear();
        feeTxt.clear();
        descriptionTxt.clear();

        try {
            programIdTxt.setText(therapyProgramBO.getNextTherapyProgramPK());
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTable() {
        ArrayList<TherapyProgramDTO> programDtos = therapyProgramBO.getAllTherapyPrograms();
        if (programDtos != null && !programDtos.isEmpty()) {
            ObservableList<TherapyProgramTM> programTMS = FXCollections.observableArrayList();
            for (TherapyProgramDTO dto : programDtos) {
                TherapyProgramTM program = new TherapyProgramTM(
                        dto.getProgramId(),
                        dto.getName(),
                        dto.getDuration(),
                        dto.getFee(),
                        dto.getDescription()
                );
                programTMS.add(program);
            }
            therapyProgramsTable.setItems(programTMS);
        } else {
            therapyProgramsTable.setItems(FXCollections.emptyObservableList());
        }
    }

    @FXML
    void btnDeleteOnAcion(ActionEvent event) {
        String programId = programIdTxt.getText();
        if (therapyProgramBO.deleteTherapyProgram(programId)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Program deleted successfully!");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete program!");
            alert.show();
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
        try {
            BigDecimal fee = new BigDecimal(feeTxt.getText().trim());
            TherapyProgramDTO programDto = new TherapyProgramDTO(
                    programIdTxt.getText(),
                    programNameTxt.getText(),
                    durationTxt.getText(),
                    fee,
                    descriptionTxt.getText()
            );

            if (therapyProgramBO.saveTherapyProgram(programDto)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Program saved successfully!");
                refreshPage();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save program!");
                alert.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid fee!");
            alert.show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String name = searchTxt.getText().trim();

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a program name to search");
            refreshPage();
            return;
        }

        ArrayList<TherapyProgramDTO> programDtos = therapyProgramBO.findTherapyProgramByName(name);

        if (programDtos != null && !programDtos.isEmpty()) {
            ObservableList<TherapyProgramTM> programTMS = FXCollections.observableArrayList();
            for (TherapyProgramDTO dto : programDtos) {
                TherapyProgramTM programTM = new TherapyProgramTM(
                        dto.getProgramId(),
                        dto.getName(),
                        dto.getDuration(),
                        dto.getFee(),
                        dto.getDescription()
                );
                programTMS.add(programTM);
            }

            therapyProgramsTable.setItems(programTMS);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No programs found with the given name");
            therapyProgramsTable.setItems(FXCollections.emptyObservableList());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            BigDecimal fee = new BigDecimal(feeTxt.getText().trim());
            TherapyProgramDTO programDto = new TherapyProgramDTO(
                    programIdTxt.getText(),
                    programNameTxt.getText(),
                    durationTxt.getText(),
                    fee,
                    descriptionTxt.getText()
            );

            if (therapyProgramBO.updateTherapyProgram(programDto)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Program updated successfully!");
                refreshPage();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update program!");
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid fee!");
            alert.show();
        }
    }

    @FXML
    void tableClick(MouseEvent event) {
        TherapyProgramTM selectedProgram = therapyProgramsTable.getSelectionModel().getSelectedItem();
        if (selectedProgram != null) {
            programIdTxt.setText(selectedProgram.getProgramId());
            programNameTxt.setText(selectedProgram.getName());
            durationTxt.setText(selectedProgram.getDuration());
            feeTxt.setText(selectedProgram.getFee().toString());
            descriptionTxt.setText(selectedProgram.getDescription());
        }
    }
}
