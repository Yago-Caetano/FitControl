package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
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
import java.util.ResourceBundle;

public class EmployeeEditScreenController implements Initializable {
    @FXML
    public RadioButton RndBtnFuncionario;
    @FXML
    public RadioButton RndBtnGerente;
    @FXML
    public TextField txtID,txtNomeColaborador,txtEmail,txtSenha;

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

        //Adiciona os RadioButton em um grupo, se um for selecionado os outros são deslesionados
        ToggleGroup tg = new ToggleGroup();
        RndBtnFuncionario.setToggleGroup(tg);
        RndBtnGerente.setToggleGroup(tg);

    }

    /***
     * Salva ou altera um cliente dependendo do valor do boolean update.
     */
    @FXML
    protected void salvarClicked() throws SQLException {
        EventManager evtManager = new EventManager();
        FuncionarioModel funcionario = new FuncionarioModel();
        FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();

        funcionario.setNome(txtNomeColaborador.getText());
        funcionario.setLogin(txtEmail.getText());
        funcionario.setSenha(txtSenha.getText());

        if(RndBtnFuncionario.isSelected())
            funcionario.setNivel(EnumTipoUsuarios.FUNCIONARIO.getCode());
        else
            funcionario.setNivel(EnumTipoUsuarios.FUNCIONARIO.getCode());   //Falta o Gerente

        PublisherTela p = new PublisherTela(evtManager);

        //Verifica se é Edit ou Insert
        if(!update){
            p.RegisterUser(funcionario);
        }
        else{
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
}
