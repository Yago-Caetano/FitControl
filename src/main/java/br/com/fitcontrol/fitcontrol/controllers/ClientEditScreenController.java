

package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;
import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
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
import java.time.LocalDate;
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
    private Button voltar;
    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws IOException {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                    navigation.getStage().setScene(scene);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void salvarClicked() {
        EventManager eventManager = new EventManager();
        FitControlContext context = new FitControlContext();
        ClienteModel cliente = new ClienteModel();

        cliente.setId(Integer.parseInt(txtID.getText()));
        cliente.setNome(txtNomeCliente.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setPontos(0);

        //Verifica se é Edit ou Insert
        if(update == false){ //Insert
            PublisherTela publisherTela = new PublisherTela(eventManager);
            publisherTela.UserRegisterEvent();

            voltarClicked();
        }
        else{   // Edit
            PublisherTela publisherTela = new PublisherTela(eventManager);
            publisherTela.UserUpdateEvent();

            voltarClicked();
            setUpdate(false);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();
    }

    void preencheTextField(ClienteModel aluno) {

        txtID.setText(Integer.toString(aluno.getId()));
        txtTelefone.setText(aluno.getTelefone());
        txtEmail.setText(aluno.getEmail());
        txtNomeCliente.setText(aluno.getNome());
    }

    void setUpdate(boolean b) {
        this.update = b;

    }
}


