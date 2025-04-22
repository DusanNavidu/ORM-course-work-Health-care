module lk.ijse.gdse.serenitymentalhealththerapycenterormcoursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.jfoenix;
    requires static lombok;
    requires java.mail;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework to javafx.fxml;
    opens lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller to javafx.fxml;
    opens lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm to javafx.base;
    opens lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity to org.hibernate.orm.core;

    exports lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework;
    exports lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.controller;
}