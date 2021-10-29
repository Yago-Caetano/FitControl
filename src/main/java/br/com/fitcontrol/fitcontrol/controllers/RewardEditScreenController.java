package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
import br.com.fitcontrol.fitcontrol.models.RecompensaModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RewardEditScreenController implements Initializable {
    @FXML
    public TextField txtID;
    @FXML
    public TextField txtTitulo;
    @FXML
    public TextField txtQtPontos;

    private NavigationSingleton navigation;
    private boolean update;
    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                    navigation.getStage().setScene(scene);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void salvarClicked() {
        EventManager evtManager = new EventManager();
        RecompensaModel recompensa = new RecompensaModel();
        PublisherTela p = new PublisherTela(evtManager);

        recompensa.setId(Integer.parseInt(txtID.getText()));
        recompensa.setDescricao(txtTitulo.getText());
        recompensa.setPontosNecessarios(Integer.parseInt(txtQtPontos.getText()));



        //Verifica se é Edit ou Insert
        if(!update){                        //Insert
            p.RegisterReward(recompensa);
            voltarClicked();
        }
        else{                               // Edit
            p.UpdateReward(recompensa);
            setUpdate(false);
            voltarClicked();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();
    }

    void setUpdate(boolean b) {
        this.update = b;

    }
}

