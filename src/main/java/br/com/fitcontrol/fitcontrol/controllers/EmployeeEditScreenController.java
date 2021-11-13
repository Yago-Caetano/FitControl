package br.com.fitcontrol.fitcontrol.controllers;

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
    public TextField txtID,txtNomeColaborador,txtEmail,txtSenha, txtTelefone;
    @FXML
    public Label lbErroNome,lbErroEmail,lbErroTelefone,lbErroSenha,lbErroNivel;

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
            if(!update && !validarDados()){
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

        }
        catch(Exception e)
        {

        }


    }

    public Boolean validarDados() throws SQLException {
        Boolean erro = false;
        FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();
        ArrayList<FuncionarioModel> lista = dao.lista();
        String mensagemDeErro = "";

        lbErroTelefone.setText("");
        lbErroNome.setText("");
        lbErroEmail.setText("");
        lbErroSenha.setText("");
        lbErroNivel.setText("");

        if(txtTelefone.getText().trim() == null || txtTelefone.getText().trim().isEmpty()){
            erro = true;
            lbErroTelefone.setText("Preencha o Telefone");
            mensagemDeErro += "Preencha o Telefone\n";
        }
        if(txtNomeColaborador.getText().trim() == null || txtNomeColaborador.getText().trim().isEmpty()){
            erro = true;
            lbErroNome.setText("Preencha o Nome");
            mensagemDeErro += "Preencha o Nome\n";
        }
        if(txtEmail.getText().trim() == null || txtEmail.getText().trim().isEmpty()){
            erro = true;
            lbErroEmail.setText("Preencha o Email");
            mensagemDeErro += "Preencha o Email\n";
        }
        if(txtSenha.getText().trim() == null || txtSenha.getText().trim().isEmpty()){
            erro = true;
            lbErroSenha.setText("Preencha a Senha");
            mensagemDeErro += "Preencha a Senha\n";
        }

        if(!RndBtnFuncionario.isSelected() && !RndBtnGerente.isSelected()){
            erro = true;
            lbErroNivel.setText("Escolha um Nível");
            mensagemDeErro += "Escolha um Nível\n";
        }

        if(erro){
            ErrorPopUpSingleton.getInstance().showError(mensagemDeErro);
            return true;
        }

        if(!txtTelefone.getText().trim().matches("[0-9]+")){
            erro = true;
            lbErroTelefone.setText("Digite apenas números");
            mensagemDeErro += "Digite apenas números no Telefone\n";
        }

        if(lista.stream().filter(c -> c.getNome().equals(txtNomeColaborador.getText().trim())).findFirst().isPresent()){
            erro = true;
            lbErroNome.setText("Nome ja está em uso");
            mensagemDeErro += "Nome ja está em uso\n";
        }

        if(lista.stream().filter(c -> c.getLogin().equals(txtEmail.getText().trim())).findFirst().isPresent()){
            erro = true;
            lbErroEmail.setText("Email ja está em uso");
            mensagemDeErro += "Email ja está em uso\n";
        }

        if(lista.stream().filter(c -> c.getTelefone().equals(txtTelefone.getText().trim())).findFirst().isPresent()){
            erro = true;
            lbErroTelefone.setText("Telefone ja está em uso");
            mensagemDeErro += "Telefone ja está em uso\n";
        }

        if(mensagemDeErro != "")
            ErrorPopUpSingleton.getInstance().showError(mensagemDeErro);

        return erro;

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

    }

    @Override
    protected void PreviousScreenDataReceived() {
        preencheTextField((FuncionarioModel) DataFromPreviousScreen);
        update = true;
    }
}
