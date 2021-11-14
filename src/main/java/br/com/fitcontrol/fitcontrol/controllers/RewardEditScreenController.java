package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.models.RecompensaModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RewardEditScreenController extends  PadrãoController implements Initializable {
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
            navigation.goBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void salvarClicked() throws SQLException {
        try{

            if(!validaDados())
                return;

            RecompensaModel recompensa = new RecompensaModel();
            PublisherTela p =  PublisherTela.getInstance();

            recompensa.setDescricao(txtTitulo.getText());
            recompensa.setPontosNecessarios(Integer.parseInt(txtQtPontos.getText()));


            //Verifica se é Edit ou Insert
            if(!update){                        //Insert
                p.RegisterReward(recompensa);
            }
            else{                               // Edit
                p.UpdateReward(recompensa);
                setUpdate(false);
            }

        }
        catch(Exception e)
        {
            
        }

    }

    public  boolean validaDados(){

        if(txtTitulo.getText().length()==0)
        {
            ErrorPopUpSingleton.getInstance().showError("Titulo Inválido");
            return false;
        }

        if(txtQtPontos.getText().length() == 0)
        {
            ErrorPopUpSingleton.getInstance().showError("Pontos Inválidos");
            return false;
        }

        try{
            Double.parseDouble(txtQtPontos.getText());
        }
        catch (Exception e)
        {
            ErrorPopUpSingleton.getInstance().showError("Pontos Inválidos");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();
    }

    void setUpdate(boolean b) {
        this.update = b;

    }


    @Override
    protected void PreviousScreenDataReceived() {

    }
}

