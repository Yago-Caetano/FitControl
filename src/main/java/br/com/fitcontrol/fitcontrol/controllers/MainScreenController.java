package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.Acesso.FuncionarioLogadoSingleton;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController extends PadrãoController implements Initializable {

    private NavigationSingleton navigation;
    private FuncionarioModel funcionario;

    @FXML
    private HBox catracaHbox,pagamentoHbox,alunoHbox,staffHbox,rewardHbox,relatHbox;

    @FXML
    private Label lbStatusCOM;
    @FXML
    private Label lbFuncionario;

    @FXML
    protected void LogoutClicked() {
        FuncionarioLogadoSingleton.getInstance().signedOut();
        executeNavigation(NavigationSingleton.LOGIN_SCREEN);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){

        navigation = NavigationSingleton.getInstance();
        navigation.setRootScreen(NavigationSingleton.MAIN_SCREEN);
        catracaHbox.addEventFilter(MouseEvent.MOUSE_CLICKED,catracaClicked);
        pagamentoHbox.addEventFilter(MouseEvent.MOUSE_CLICKED,pagamentoClicked);
        alunoHbox.addEventFilter(MouseEvent.MOUSE_CLICKED,alunoClicked);
        staffHbox.addEventFilter(MouseEvent.MOUSE_CLICKED,staffClicked);
        rewardHbox.addEventFilter(MouseEvent.MOUSE_CLICKED,rewardClicked);
        relatHbox.addEventFilter(MouseEvent.MOUSE_CLICKED,relatClicked);

        SerialCommunicatorSingleton serial = SerialCommunicatorSingleton.getInstance();

        if(serial.isConnected())
            lbStatusCOM.setText("Catracas Conectadas");
        else
            lbStatusCOM.setText("Catracas Desconectadas");

        lbFuncionario.setText(FuncionarioLogadoSingleton.getInstance().getEmployeeSigned().getNome().toUpperCase());

    }

    //Creating the mouse event handler
    EventHandler<MouseEvent> catracaClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            executeNavigation(NavigationSingleton.CATRACA_SCREEN);
        }
    };

    //Creating the mouse event handler
    EventHandler<MouseEvent> pagamentoClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            executeNavigation(NavigationSingleton.PAYMENT_SCREEN);
        }
    };


    //Creating the mouse event handler
    EventHandler<MouseEvent> alunoClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            executeNavigation(NavigationSingleton.CLIENTS_SCREEN);
        }
    };


    //Creating the mouse event handler
    EventHandler<MouseEvent> staffClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            executeNavigation(NavigationSingleton.STAFF_SCREEN);
        }
    };

    //Creating the mouse event handler
    EventHandler<MouseEvent> rewardClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            executeNavigation(NavigationSingleton.REWARDS_SCREEN);
        }
    };


    //Creating the mouse event handler
    EventHandler<MouseEvent> relatClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            executeNavigation(NavigationSingleton.REPORT_SCREEN);

        }
    };



    private void executeNavigation(int screenId)
    {
        navigation.navigate(screenId);
    }

    @Override
    protected void PreviousScreenDataReceived() {

    }
}
