package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.Acesso.FuncionarioLogadoSingleton;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
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

public class EmployeeEditScreenController extends PadrãoController implements Initializable {
    @FXML
    public RadioButton RndBtnFuncionario;
    @FXML
    public RadioButton RndBtnGerente;
    @FXML
    public TextField txtID,txtNomeColaborador,txtEmail, txtTelefone;

    @FXML
    public PasswordField txtSenha;

    @FXML
    public Label lbErroNome,lbErroEmail,lbErroTelefone,lbErroSenha,lbErroNivel,lbNivel;


    private NavigationSingleton navigation;
    private boolean update;

    private FuncionarioModel mFuncionario;

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

        //Adiciona os RadioButton em um grupo, se um for selecionado os outros são deslesionados
        ToggleGroup tg = new ToggleGroup();
        RndBtnFuncionario.setToggleGroup(tg);
        RndBtnGerente.setToggleGroup(tg);

        if(FuncionarioLogadoSingleton.getInstance().getEmployeeSigned() == null || FuncionarioLogadoSingleton.getInstance().getEmployeeSigned().getNivel() != EnumTipoUsuarios.GERENTE.getCode())
        {
            RndBtnFuncionario.setVisible(false);
            RndBtnGerente.setVisible(false);
            lbNivel.setVisible(false);
        }

    }

    /***
     * Salva ou altera um cliente dependendo do valor do boolean update.
     */
    @FXML
    protected void salvarClicked() throws SQLException {
        try{

            if(!validarDados())
                return;

            if(!update)
                mFuncionario = new FuncionarioModel();

            FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();

            mFuncionario.setNome(txtNomeColaborador.getText());
            mFuncionario.setLogin(txtEmail.getText());
            mFuncionario.setSenha(txtSenha.getText());
            mFuncionario.setTelefone(txtTelefone.getText());

            if(!RndBtnFuncionario.isVisible() || RndBtnFuncionario.isSelected())
                mFuncionario.setNivel(EnumTipoUsuarios.FUNCIONARIO.getCode());
            else
                mFuncionario.setNivel(EnumTipoUsuarios.GERENTE.getCode());

            PublisherTela p = PublisherTela.getInstance();

            //Verifica se é Edit ou Insert
            if(!update){
                p.RegisterEmployee(mFuncionario);
            }
            else{
                p.UpdateEmployee(mFuncionario);
                setUpdate(false);
            }

        }
        catch(Exception e)
        {

        }


    }

    public Boolean validarDados() throws SQLException {
        Boolean ret = true;
        FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();
        ArrayList<FuncionarioModel> lista = dao.lista();
        String mensagemDeErro = "";

        lbErroTelefone.setText("");
        lbErroNome.setText("");
        lbErroEmail.setText("");
        lbErroSenha.setText("");
        lbErroNivel.setText("");

        if(txtTelefone.getText().trim().isEmpty()){
            ret = false;
            lbErroTelefone.setText("Preencha o Telefone");
            mensagemDeErro += "Preencha o Telefone\n";
        }
        if( txtNomeColaborador.getText().trim().isEmpty()){
            ret = false;
            lbErroNome.setText("Preencha o Nome");
            mensagemDeErro += "Preencha o Nome\n";
        }
        if( txtEmail.getText().trim().isEmpty()){
            ret = false;
            lbErroEmail.setText("Preencha o Email");
            mensagemDeErro += "Preencha o Email\n";
        }
        if( txtSenha.getText().trim().isEmpty()){
            ret = false;
            lbErroSenha.setText("Preencha a Senha");
            mensagemDeErro += "Preencha a Senha\n";
        }

        if(!RndBtnFuncionario.isSelected() && !RndBtnGerente.isSelected()){
            ret = false;
            lbErroNivel.setText("Escolha um Nível");
            mensagemDeErro += "Escolha um Nível\n";
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

        if(funcionario.getNivel() == EnumTipoUsuarios.FUNCIONARIO.getCode())
            RndBtnFuncionario.setSelected(true);
        else
            RndBtnGerente.setSelected(true);

    }

    @Override
    protected void PreviousScreenDataReceived() {
        preencheTextField((FuncionarioModel) DataFromPreviousScreen);
        update = true;
        mFuncionario = (FuncionarioModel) DataFromPreviousScreen;
    }
}
