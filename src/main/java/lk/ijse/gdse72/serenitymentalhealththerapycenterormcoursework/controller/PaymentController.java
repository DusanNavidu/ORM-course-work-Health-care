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
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.impl.PatientDAOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dao.custom.impl.TherapyProgramsDAOImpl;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto.PaymentDTO;
import lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm.PaymentTM;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    // === FXML Elements ===
    @FXML private JFXButton btnDelete, btnResert, btnSave, btnSearch, btnUpdate;
    @FXML private TableColumn<PaymentTM, ?> colAmont, colPatientID, colPaymenetDate, colPaymentId, colPaymentTime, colProgramId;
    @FXML private ComboBox<String> comPatientId, comProgramId;
    @FXML private Label lblAmont, lblPatientName, lblPaymentDate, lblPaymentID, lblPaymentTime, lblProgramId;
    @FXML private AnchorPane pagePayment;
    @FXML private TableView<PaymentTM> tblPayemnt;

    // === Data ===
    private final ObservableList<PaymentTM> paymentList = FXCollections.observableArrayList();
    private PaymentBO paymentBO;
    private final TherapyProgramsDAOImpl therapyProgramsDAO = new TherapyProgramsDAOImpl();
    private final PatientDAOImpl patientDAO = new PatientDAOImpl();
    private String selectedPaymentId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.PAYMENT);
        setCellValueFactory();
        loadPaymentData();
        setTableListener();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

//        comProgramId.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
//            try {
//                lblProgramId.setText(newVal != null ? therapyProgramsDAO.getTherapyProgramNameById(newVal) : "");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        comPatientId.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            try {
                lblPatientName.setText(newVal != null ? patientDAO.getPatientNameById(newVal) : "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            for (PaymentDTO dto : payments) {
                paymentList.add(new PaymentTM(
                        dto.getPaymentId(),
                        dateFormat.format(dto.getDate()),
                        timeFormat.format(dto.getTime()),
                        dto.getProgramId(),
                        dto.getPatientId(),
                        dto.getAmount()
                ));
            }

            tblPayemnt.setItems(paymentList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load payment data!").show();
        }
    }

    private void setTableListener() {
        tblPayemnt.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) setPaymentData(newVal);
        });
    }

    private void setPaymentData(PaymentTM payment) {
        selectedPaymentId = String.valueOf(payment.getPaymentId());
        lblPaymentID.setText(String.valueOf(payment.getPaymentId()));
        lblPaymentDate.setText(payment.getDate());
        lblPaymentTime.setText(payment.getTime());
        comProgramId.setValue(String.valueOf(payment.getProgramId()));
        comPatientId.setValue(String.valueOf(payment.getPatientId()));
        lblAmont.setText(payment.getAmount());

        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            Date date = dateFormat.parse(lblPaymentDate.getText());
            Date time = timeFormat.parse(lblPaymentTime.getText());
            int programId = Integer.parseInt(comProgramId.getValue());
            int patientId = Integer.parseInt(comPatientId.getValue());
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

    @FXML void btnResertOnAction(ActionEvent event) {
        refreshPage();
    }

    @FXML void btnDeleteOnAcion(ActionEvent event) {
        // Add delete logic here later
    }

    @FXML void btnUpdateOnAction(ActionEvent event) {
        // Add update logic here later
    }

    @FXML void btnSearchOnAction(ActionEvent event) {
        // Add search logic here later
    }
}
