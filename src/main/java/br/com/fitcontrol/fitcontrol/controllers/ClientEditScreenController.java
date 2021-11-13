

package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientEditScreenController extends PadrãoController implements Initializable {
    public TextField txtEmail,txtTelefone,txtNomeCliente,txtID;
    @FXML
    public Label lbErroNome,lbErroEmail,lbErroTelefone;


    private NavigationSingleton navigation;
    private boolean update;

    private ClienteModel mClient;

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
    protected void salvarClicked() throws Exception {
        try {
                if(!validarDados())
                    return;

                if(!update)
                    mClient = new ClienteModel();

                ClienteMySQLDAO dao = new ClienteMySQLDAO();

                mClient.setNome(txtNomeCliente.getText());
                mClient.setLogin(txtEmail.getText());
                mClient.setTelefone(txtTelefone.getText());
                mClient.setPontos(0);


                PublisherTela p = PublisherTela.getInstance();

                //Verifica se é Edit ou Insert
                if (!update) {                        //Insert
                    p.RegisterUser(mClient);
                } else {                            // Edit
                    p.UpdateUser(mClient);

            }

        }
        catch(Exception e){
        }
    }


    public Boolean validarDados() throws SQLException {
        Boolean ret = true;
        ClienteMySQLDAO dao = new ClienteMySQLDAO();
        ArrayList<ClienteModel> lista = dao.lista();
        String mensagemDeErro = "";

        lbErroTelefone.setText("");
        lbErroNome.setText("");
        lbErroEmail.setText("");

        if(txtTelefone.getText().trim().isEmpty()){
            ret = false;
            lbErroTelefone.setText("Preencha o Telefone");
            mensagemDeErro += "Preencha o Telefone\n";
        }
        if( txtNomeCliente.getText().trim().isEmpty()){
            ret = false;
            lbErroNome.setText("Preencha o Nome");
            mensagemDeErro += "Preencha o Nome\n";
        }
        if(txtEmail.getText().trim().isEmpty()){
            ret = false;
            lbErroEmail.setText("Preencha o Email");
            mensagemDeErro += "Preencha o Email\n";
        }

        if(!ret){
            ErrorPopUpSingleton.getInstance().showError(mensagemDeErro);
            return ret;
        }


        if(!txtTelefone.getText().trim().matches("[0-9]+")){
            ret = false;
            lbErroTelefone.setText("Digite apenas números");
            mensagemDeErro += "Digite apenas números no Telefone\n";
        }


        if(mensagemDeErro != "")
            ErrorPopUpSingleton.getInstance().showError(mensagemDeErro);

        return ret;

    }

    /**
     * Preenche os TextFields da tela com o cliente que deseja editar.
     */
    void preencheTextField(ClienteModel aluno) {

        txtID.setText((aluno.getId()));
        txtTelefone.setText(aluno.getTelefone());
        txtEmail.setText(aluno.getLogin());
        txtNomeCliente.setText(aluno.getNome());

        txtID.setEditable(false);
        txtID.setDisable(true);
    }

    private void executeNavigation(int screenId)
    {
        navigation.navigate(screenId);
    }


    @Override
    protected void PreviousScreenDataReceived() {
        preencheTextField((ClienteModel) DataFromPreviousScreen);
        update = true;
        mClient = (ClienteModel) DataFromPreviousScreen;
    }
}
