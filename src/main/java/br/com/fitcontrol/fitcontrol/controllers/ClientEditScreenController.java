

package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
import br.com.fitcontrol.fitcontrol.models.UsuarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.publishers.PublisherSerial;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientEditScreenController implements Initializable {
    @FXML
    public TextField txtID;
    @FXML
    public TextField txtNomeCliente;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtTelefone;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();
        txtID.setDisable(true);
        txtID.setVisible(false);
    }

    /***
     * Salva ou altera um cliente dependendo do valor do boolean update.
     */
    @FXML
    protected void salvarClicked() throws SQLException {

        ClienteModel cliente = new ClienteModel();
        ClienteMySQLDAO dao = new ClienteMySQLDAO();

        cliente.setNome(txtNomeCliente.getText());
        cliente.setLogin(txtEmail.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setPontos(0);


        PublisherTela p = PublisherTela.getInstance();

        //Verifica se é Edit ou Insert
        if(!update){                        //Insert
            p.RegisterUser(cliente);
        }
        else{                            // Edit
            cliente = (ClienteModel) (dao.localiza(Integer.parseInt(txtID.getText())));
            cliente.setId(Integer.parseInt(txtID.getText()));
            cliente.setNome(txtNomeCliente.getText());
            cliente.setLogin(txtEmail.getText());
            cliente.setTelefone(txtTelefone.getText());
            cliente.setPontos(0);
            p.UpdateUser(cliente);
            setUpdate(false);
        }
        voltarClicked();

    }

    /**
     * Preenche os TextFields da tela com o cliente que deseja editar.
     */
    void preencheTextField(ClienteModel aluno) {

        txtID.setText(Integer.toString(aluno.getId()));
        txtTelefone.setText(aluno.getTelefone());
        txtEmail.setText(aluno.getLogin());
        txtNomeCliente.setText(aluno.getNome());

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
                Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                navigation.getStage().setScene(scene);
            }
        });
    }
}
