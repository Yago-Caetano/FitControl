

package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientEditScreenController implements Initializable {
    public TextField txtEmail,txtTelefone,txtNomeCliente,txtID;
    @FXML
    public Label lbErroNome,lbErroEmail,lbErroTelefone;


    private NavigationSingleton navigation;
    private boolean update;
    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                    navigation.getStage().setScene(scene);
                }
            });
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

                ClienteModel cliente = new ClienteModel();
                ClienteMySQLDAO dao = new ClienteMySQLDAO();

                cliente.setNome(txtNomeCliente.getText());
                cliente.setLogin(txtEmail.getText());
                cliente.setTelefone(txtTelefone.getText());
                cliente.setPontos(0);


                PublisherTela p = PublisherTela.getInstance();

                //Verifica se é Edit ou Insert
                if (!update && !validarDados()) {                        //Insert
                    p.RegisterUser(cliente);
                } else {                            // Edit
                    cliente = (ClienteModel) (dao.localiza(txtID.getText()));
                    cliente.setNome(txtNomeCliente.getText());
                    cliente.setLogin(txtEmail.getText());
                    cliente.setTelefone(txtTelefone.getText());
                    cliente.setPontos(0);
                    p.UpdateUser(cliente);
                    setUpdate(false);

                voltarClicked();

            }

        }
        catch(Exception e){
        }
    }


    public Boolean validarDados() throws SQLException {
        Boolean erro = false;
        ClienteMySQLDAO dao = new ClienteMySQLDAO();
        ArrayList<ClienteModel> lista = dao.lista();

        lbErroTelefone.setText("");
        lbErroNome.setText("");
        lbErroEmail.setText("");

        if(txtTelefone.getText().trim() == null || txtTelefone.getText().trim().isEmpty()){
            erro = true;
            lbErroTelefone.setText("Preencha o Telefone");
        }
        if(txtNomeCliente.getText().trim() == null || txtNomeCliente.getText().trim().isEmpty()){
            erro = true;
            lbErroNome.setText("Preencha o Nome");
        }
        if(txtEmail.getText().trim() == null || txtEmail.getText().trim().isEmpty()){
            erro = true;
            lbErroEmail.setText("Preencha o Email");
        }

        if(erro)
            return true;

        if(!txtTelefone.getText().trim().matches("[0-9]+")){
            erro = true;
            lbErroTelefone.setText("Digite apenas números");
        }

        if(lista.stream().filter(c -> c.getNome().equals(txtNomeCliente.getText().trim())).findFirst().isPresent()){
            erro = true;
            lbErroNome.setText("Nome ja está em uso");
        }

        if(lista.stream().filter(c -> c.getLogin().equals(txtEmail.getText().trim())).findFirst().isPresent()){
            erro = true;
            lbErroEmail.setText("Email ja está em uso");
        }

        if(lista.stream().filter(c -> c.getTelefone().equals(txtTelefone.getText().trim())).findFirst().isPresent()){
            erro = true;
            lbErroTelefone.setText("Email ja está em uso");
        }

        return erro;

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

    void setUpdate(boolean b) {
        this.update = b;
    }

    private void executeNavigation(int screenId)
    {
        navigation.navigate(screenId, new iNavCallback() {
            @Override
            public void navigateCb(String screenName) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                navigation.getStage().setScene(scene);
            }
        });
    }
}
