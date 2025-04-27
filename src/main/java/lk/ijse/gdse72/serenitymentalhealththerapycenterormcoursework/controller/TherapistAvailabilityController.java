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
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.BOFactory;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapistAvailabilityBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.TherapistBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapistAvailabilityDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.TherapistDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.TherapistAvailabilityTM;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class TherapistAvailabilityController implements Initializable {

    @FXML
    private TextField availabilityIdTxt;

    @FXML
    private TableColumn<TherapistAvailabilityTM, String> availableDateCol;

    @FXML
    private DatePicker availableDateTxt;

    @FXML
    private TableColumn<TherapistAvailabilityTM, String> availableIdCol;

    @FXML
    private TableColumn<?, ?> availableStatusCol;

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
    private TableColumn<TherapistAvailabilityTM, LocalTime> endTimeCol;

    @FXML
    private TextField endTimeTxt;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<TherapistAvailabilityTM, String> startTimeCol;

    @FXML
    private TextField startTimeTxt;

    @FXML
    private ChoiceBox<String> statusTxt;

    @FXML
    private TableView<TherapistAvailabilityTM> therapistAvailabilityTable;

    @FXML
    private TableColumn<TherapistAvailabilityTM, String> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private TextField therapistNameTxt;

    @FXML
    private JFXButton therapistSearchBtn;

    private final TherapistAvailabilityBO availabilityBO = (TherapistAvailabilityBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST_AVAILABILITY);
    private final TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        statusTxt.getItems().addAll("Available", "Not Available");
        statusTxt.setValue("Available");
        initializeColumns();
        refreshPage();
    }

    private void initializeColumns() {
        availableIdCol.setCellValueFactory(new PropertyValueFactory<>("availabilityId"));
        therapistIdCol.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        availableDateCol.setCellValueFactory(new PropertyValueFactory<>("availableDate"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        availableStatusCol.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
    }

    private void refreshPage() {
        availabilityIdTxt.clear();
        therapistIdTxt.clear();
        therapistNameTxt.clear();
        availableDateTxt.setValue(null);
        statusTxt.setValue("Available");

        availabilityIdTxt.setText(availabilityBO.getNextPK());
        refreshTable();
    }

    private void refreshTable() {
        ArrayList<TherapistAvailabilityDTO> availabilityDtos = availabilityBO.getAllAvailabilities();
        ObservableList<TherapistAvailabilityTM> items = FXCollections.observableArrayList();

        for (TherapistAvailabilityDTO dto : availabilityDtos) {
            items.add(new TherapistAvailabilityTM(
                    dto.getAvailabilityId(),
                    dto.getTherapistId(),
                    dto.getAvailableDate(),
                    dto.getStartTime(),
                    dto.getEndTime(),
                    dto.isAvailable()
            ));
        }

        therapistAvailabilityTable.setItems(items);
    }

    @FXML
    void btnDeleteOnAcion(ActionEvent event) {
        String id = availabilityIdTxt.getText();
        if (availabilityBO.deleteAvailability(id)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Availability deleted successfully");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete availability");
        }
    }

    @FXML
    void btnEmailOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Email sent implementation is not available yet.");
    }

    @FXML
    void btnResertOnAction(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        TherapistAvailabilityDTO dto = getDtoFromFields();
        if (dto != null && availabilityBO.saveAvailability(dto)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Availability saved successfully");
            refreshPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save availability");
        }
    }

    private TherapistAvailabilityDTO getDtoFromFields() {
        try {
            String timeInput = startTimeTxt.getText().trim();
            String durationInput = endTimeTxt.getText().trim();

            if (timeInput.isEmpty() || durationInput.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in all fields.");
                return null;
            }

            LocalTime startTime;
            LocalTime endTime;
            try {
                DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern("hh:mm a")
                        .toFormatter(Locale.ENGLISH);

                startTime = LocalTime.parse(startTimeTxt.getText().trim(), formatter);
                endTime = LocalTime.parse(endTimeTxt.getText().trim(), formatter);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid time format. Please use hh:mm AM/PM.");
                return null;
            }
            if (startTime.isAfter(endTime)) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Start time cannot be after end time.");
                return null;
            }

            if (availableDateTxt.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an available date.");
                return null;
            }

            if (therapistIdTxt.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a therapist ID.");
                return null;
            }

            return new TherapistAvailabilityDTO(
                    availabilityIdTxt.getText(),
                    therapistIdTxt.getText(),
                    availableDateTxt.getValue(),
                    startTime,
                    endTime,
                    null,
                    statusTxt.getValue().equals("Available")
            );

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while processing the data.");
            return null;
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String therapistName = searchTxt.getText().trim();
        if (therapistName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a therapist name to search.");
            refreshPage();
            return;
        }

        ArrayList<TherapistAvailabilityDTO> results = availabilityBO.findByTherapist(therapistName);
        ObservableList<TherapistAvailabilityTM> list = FXCollections.observableArrayList();

        for (TherapistAvailabilityDTO dto : results) {
            list.add(new TherapistAvailabilityTM(
                    dto.getAvailabilityId(),
                    dto.getTherapistId(),
                    dto.getAvailableDate(),
                    dto.getStartTime(),
                    dto.getEndTime(),
                    dto.isAvailable()
            ));
        }

        if (list.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No availability found for the given therapist.");
            therapistAvailabilityTable.setItems(FXCollections.emptyObservableList());
        } else {
            therapistAvailabilityTable.setItems(list);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void tableClick(MouseEvent event) {
        TherapistAvailabilityTM selected = therapistAvailabilityTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            availabilityIdTxt.setText(selected.getAvailabilityId());
            therapistIdTxt.setText(selected.getTherapistId());
            therapistNameTxt.setText(therapistBO.findByTherapistId(selected.getTherapistId()).getName());
            availableDateTxt.setValue(selected.getAvailableDate());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
            startTimeTxt.setText(selected.getStartTime().format(formatter));
            endTimeTxt.setText(selected.getEndTime().format(formatter));
            statusTxt.setValue(selected.isAvailable() ? "Available" : "Not Available");
        }
    }

    @FXML
    void therapistSearchBtnOnAction(ActionEvent event) {
        String name = therapistNameTxt.getText().trim();
        ArrayList<TherapistDTO> therapists = therapistBO.findByTherapistName(name);

        if (therapists.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No therapist found with the given name.");
            return;
        }

        TherapistDTO therapist = therapists.getFirst();

        therapistIdTxt.setText(therapist.getTherapistId());
        therapistNameTxt.setText(therapist.getName());
    }
}
