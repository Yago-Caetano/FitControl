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
    public TextField txtID,txtNomeColaborador,txtEmail,txtSenha, txtTelefone;

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
        try{
            FuncionarioModel funcionario = new FuncionarioModel();
            FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();

            funcionario.setNome(txtNomeColaborador.getText());
            funcionario.setLogin(txtEmail.getText());
            funcionario.setSenha(txtSenha.getText());
            funcionario.setTelefone(txtTelefone.getText());

            if(RndBtnFuncionario.isSelected())
                funcionario.setNivel(EnumTipoUsuarios.FUNCIONARIO.getCode());
            else
                funcionario.setNivel(EnumTipoUsuarios.FUNCIONARIO.getCode());   //Falta o Gerente

            PublisherTela p = PublisherTela.getInstance();

            //Verifica se é Edit ou Insert
            if(!update){
                p.RegisterEmployee(funcionario);
            }
            else{
                funcionario = (FuncionarioModel) (dao.localiza(txtID.getText()));
                funcionario.setId(txtID.getText());
                funcionario.setNome(txtNomeColaborador.getText());
                funcionario.setLogin(txtEmail.getText());
                funcionario.setSenha(txtSenha.getText());
                funcionario.setTelefone(txtTelefone.getText());
                funcionario.setNivel(EnumTipoUsuarios.FUNCIONARIO.getCode());
                p.UpdateEmployee(funcionario);
                setUpdate(false);
            }
            voltarClicked();
        }
        catch(Exception e)
        {

        }


    }

    void setUpdate(boolean b) {
        this.update = b;
    }

    /**
     * Preenche os TextFields da tela com o cliente que deseja editar.
     */
    void preencheTextField(FuncionarioModel funcionario) {

        txtID.setText(funcionario.getId());
        txtNomeColaborador.setText(funcionario.getNome());
        txtTelefone.setText(funcionario.getTelefone());
        txtEmail.setText(funcionario.getLogin());
        txtSenha.setText(funcionario.getSenha());

    }
}
