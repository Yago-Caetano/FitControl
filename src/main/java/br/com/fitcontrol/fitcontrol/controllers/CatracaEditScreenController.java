package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.dao.Catraca.CatracaMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CatracaEditScreenController extends PadrãoController implements Initializable {
    @FXML
    public TextField txtModelo;

    private CatracaModel mCatraca;

    private NavigationSingleton navigation;
    private boolean update;
    @FXML
    private Button voltar;
    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();
    }

    /***
     * Salva ou altera um cliente dependendo do valor do boolean update.
     */
    @FXML
    protected void salvarClicked() {
        try{

            //inserir valida dados
            if(!validarDados())
                return;

            if(!update)
                mCatraca = new CatracaModel();

            CatracaMySQLDAO dao = new CatracaMySQLDAO();

            mCatraca.setModelo(txtModelo.getText());

            PublisherTela p = PublisherTela.getInstance();

            //Verifica se é Edit ou Insert
            if(!update){                        //Insert
                p.RegisterCatraca(mCatraca);
            }
            else{                            // Edit
                p.UpdateCatraca(mCatraca);
                setUpdate(false);
            }
        }
        catch(Exception e)
        {
            ErrorPopUpSingleton.getInstance().showError("Falha ao atualizar");
        }


    }


    public Boolean validarDados() throws SQLException {
        Boolean ret = true;

        if(txtModelo.getText().length() == 0)
        {
            ErrorPopUpSingleton.getInstance().showError("Insira o modelo");
            return false;
        }

        return true;

    }




    /**
     * Preenche os TextFields da tela com a catraca que deseja editar.
     */
    void preencheTextField(CatracaModel catraca) {

        txtModelo.setText(catraca.getModelo());
    }

    void setUpdate(boolean key) {
        this.update = key;
    }



    @Override
    public Object getDataFromPreviousScreen() {
        return null;
    }

    @Override
    protected void PreviousScreenDataReceived() {
        preencheTextField((CatracaModel) DataFromPreviousScreen);
        update = true;
        mCatraca = (CatracaModel) DataFromPreviousScreen;
    }
}


