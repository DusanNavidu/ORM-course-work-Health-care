module lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires com.jfoenix;
    requires static lombok;
    requires java.mail;

    opens lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework to javafx.fxml;
    opens lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller to javafx.fxml;
    opens lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.view.tdm to javafx.base;

//    exports lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework;
    exports lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;
}