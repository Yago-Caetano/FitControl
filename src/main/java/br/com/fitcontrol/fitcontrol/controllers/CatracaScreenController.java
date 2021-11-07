package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Catraca.CatracaMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
<<<<<<< Updated upstream
import java.util.ResourceBundle;
=======
import java.sql.SQLException;
import java.util.*;
>>>>>>> Stashed changes

public class CatracaScreenController implements Initializable {


    @FXML
    public TableView<CatracaModel> tabela;
    @FXML
    public TableColumn<CatracaModel, Integer> id;
    @FXML
    public TableColumn<CatracaModel, String> modelo;

    private NavigationSingleton navigation;
    @FXML
    private Button voltar, novoCliente;

    @FXML
    private Button liberarAcesso, bloquearAcesso, reiniciarCatraca;

    @FXML
    protected void voltarClicked() {
        executeNavigation(NavigationSingleton.MAIN_SCREEN);
    }

    @FXML
    protected void novaCatracaClicked() {
        executeNavigation(NavigationSingleton.CATRACA_EDIT_SCREEN);
    }

    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();

        id.setCellValueFactory(
                new PropertyValueFactory<CatracaModel, Integer>("id"));
        modelo.setCellValueFactory(
                new PropertyValueFactory<CatracaModel, String>("modelo"));

<<<<<<< Updated upstream
        ObservableList<CatracaModel> list = FXCollections.observableArrayList(new CatracaModel(1, "Catraca 1"),
                new CatracaModel(2, "Catraca 2"), new CatracaModel(3, "Catraca 3"));

        tabela.setItems(list);
=======
        try {
            carregarDados();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setUpSerialOptions();
    }

    private void setUpSerialOptions()
    {
        btConectar.addEventFilter(MouseEvent.MOUSE_CLICKED,btConectaClicked);

        fillSerialPortSpinner();

        if(SerialCommunicatorSingleton.getInstance().isConnected())
        {
            btConectar.setText("DESCONECTAR");
            spCOMPorts.setDisable(true);
        }

    }


    private void fillSerialPortSpinner()
    {
        //take available serial ports
        SerialCommunicatorSingleton serial = SerialCommunicatorSingleton.getInstance();
        List<String> portas = new ArrayList<String>();

        serialPortsMap = new HashMap<String,SerialPort>();


        for(SerialPort s : serial.getAvailablePorts())
        {
            portas.add(s.getSystemPortName());
            serialPortsMap.put(s.getSystemPortName(),s);
        }

        ObservableList<String> ports = FXCollections.observableList(portas);

        SpinnerValueFactory<String> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(ports);

        spCOMPorts.setValueFactory(valueFactory);
>>>>>>> Stashed changes
    }

    private void executeNavigation(int screenId) {
        navigation.navigate(screenId, new iNavCallback() {
            @Override
            public void navigateCb(String screenName) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                navigation.getStage().setScene(scene);
            }
        });
    }

<<<<<<< Updated upstream
=======
    //Creating the mouse event handler
    EventHandler<MouseEvent> btConectaClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {


            try
            {
                if(serialPortsMap.size()==0)
                    return;

                SerialCommunicatorSingleton Serial = SerialCommunicatorSingleton.getInstance();

                if(!Serial.isConnected())
                {
                    Serial.connect(serialPortsMap.get(spCOMPorts.getValue()));
                    btConectar.setText("DESCONECTAR");
                    spCOMPorts.setDisable(true);
                }
                else
                {
                    Serial.disconnect();
                    btConectar.setText("CONECTAR");
                    spCOMPorts.setDisable(false);
                }


            }
            catch(Exception exception)
            {
                btConectar.setText("CONECTAR");
                spCOMPorts.setDisable(false);
            }


        }
    };
    ObservableList<CatracaModel>  list = FXCollections.observableArrayList();
    CatracaMySQLDAO daoCatraca = new CatracaMySQLDAO();

    public void carregarDados() throws SQLException {
        ObservableList<CatracaModel> lista = FXCollections.observableArrayList(daoCatraca.lista());
        tabela.setItems(lista);
    }

>>>>>>> Stashed changes
    @FXML
    protected void liberarAcessoClicked() {
        
    }

    @FXML
    protected void bloquearAcessoClicked() {

    }

    @FXML
    protected void reiniciarCatracaClicked() {

    }
}