module br.com.fitcontrol.fitcontrol {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires com.fazecast.jSerialComm;



    opens br.com.fitcontrol.fitcontrol.controllers to javafx.fxml;
    opens br.com.fitcontrol.fitcontrol.models;
    exports br.com.fitcontrol.fitcontrol;
    exports br.com.fitcontrol.fitcontrol.controllers;
}