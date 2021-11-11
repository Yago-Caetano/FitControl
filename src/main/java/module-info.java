module br.com.fitcontrol.fitcontrol {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires java.sql;

    requires com.fazecast.jSerialComm;


    opens br.com.fitcontrol.fitcontrol.controllers to javafx.fxml;
    opens br.com.fitcontrol.fitcontrol.models;
    opens br.com.fitcontrol.fitcontrol.Basis;
    exports br.com.fitcontrol.fitcontrol;
    exports br.com.fitcontrol.fitcontrol.controllers;
    opens br.com.fitcontrol.fitcontrol.models.relatorios;
}