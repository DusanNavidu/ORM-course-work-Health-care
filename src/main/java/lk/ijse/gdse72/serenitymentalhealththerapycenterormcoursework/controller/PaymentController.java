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
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.bo.custom.PaymentBO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PaymentDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.PaymentTM;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

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
    private TableColumn<?, ?> colAmont;

    @FXML
    private TableColumn<?, ?> colPatientID;

    @FXML
    private TableColumn<?, ?> colPaymenetDate;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentTime;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private ComboBox<?> comPatientId;

    @FXML
    private ComboBox<?> comProgramId;

    @FXML
    private Label lblAmont;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblPaymentDate;

    @FXML
    private Label lblPaymentID;

    @FXML
    private Label lblPaymentTime;

    @FXML
    private Label lblProgramId;

    @FXML
    private AnchorPane pagePayment;

    @FXML
    private TableView<?> tblPayemnt;

    private String selectedPaymentId;
    private final ObservableList<PaymentTM> paymentList = FXCollections.observableArrayList();
    private PaymentBO paymentBO;

    @FXML
    void btnDeleteOnAcion(ActionEvent event) {

    }

    @FXML
    void btnResertOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            String date = lblPaymentDate.getText();
            String time = lblPaymentTime.getText();
            int programId = Integer.parseInt(comProgramId.getValue().toString());
            int patientId = Integer.parseInt(comPatientId.getValue().toString());
            String amount = lblAmont.getText();

            PaymentDTO paymentDTO = new PaymentDTO(date, time, programId, patientId, amount);

            boolean isSaved = paymentBO.savePayment(paymentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Payment saved successfully!").show();
                refreshPage();
                loadPaymentData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save payment!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while saving payment!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.PAYMENT);
        setCellValueFactory();
        loadPaymentData();
        setTableListener();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPaymenetDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPaymentTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colAmont.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void loadPaymentData() {
        paymentList.clear();
        try {
            List<PaymentDTO> payments = paymentBO.getAllPayments();
            for (PaymentDTO dto : payments) {
                paymentList.add(new PaymentTM(
                        dto.getPaymentId(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getProgramId(),
                        dto.getPatientId(),
                        dto.getAmount()
                ));
            }
            tblPayemnt.setItems(paymentList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTableListener() {
        tblPayemnt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setPaymentData(newValue);
            }
        });
    }

    private void setPaymentData(PaymentTM payment) {
        selectedPaymentId = String.valueOf(payment.getPaymentId());
        lblPaymentID.setText(String.valueOf(payment.getPaymentId()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        lblPaymentDate.setText(dateFormat.format(payment.getDate()));
        lblPaymentTime.setText(timeFormat.format(payment.getTime()));

        comProgramId.setValue(String.valueOf(payment.getProgramId()));
        comPatientId.setValue(String.valueOf(payment.getPatientId()));

        lblAmont.setText(String.valueOf(payment.getAmount()));

        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    private void refreshPage() {
        selectedPaymentId = null;
        lblPaymentID.setText("");
        lblPaymentDate.setText("");
        lblPaymentTime.setText("");
        comProgramId.setValue(null);
        comPatientId.setValue(null);
        lblAmont.setText("");

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }
}
