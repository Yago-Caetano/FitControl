module br.com.fitcontrol.fitcontrol {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.fitcontrol.fitcontrol to javafx.fxml;
    exports br.com.fitcontrol.fitcontrol;
    exports br.com.fitcontrol.fitcontrol.controllers;
}