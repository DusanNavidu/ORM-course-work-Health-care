package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TherapistsController {

    @FXML
    private RadioButton RNo;

    @FXML
    private RadioButton RYes;

    @FXML
    private AnchorPane TherapistsPage;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEmail;

    @FXML
    private JFXButton btnResert;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnsave;

    @FXML
    private TableColumn<?, ?> colTherapistsID;

    @FXML
    private TableColumn<?, ?> comSpecialization;

    @FXML
    private TableColumn<?, ?> comTherapistsAvailability;

    @FXML
    private TableColumn<?, ?> comTherapistsEmail;

    @FXML
    private TableColumn<?, ?> comTherapistsName;

    @FXML
    private TableColumn<?, ?> comTherapistsPhone;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblTherapists;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    private String selectTherapistId;
//    private final ObservableList<>

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmailOnAction(ActionEvent event) {

    }

    @FXML
    void btnResertOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {

    }

}
