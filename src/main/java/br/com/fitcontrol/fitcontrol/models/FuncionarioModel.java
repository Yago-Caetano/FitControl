package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;

public class FuncionarioModel  extends Entidade {

    private EnumTipoUsuarios tipo;
    private String nome;
    private byte nivel;
    private String login;
    private String senha;
    private String Telefone;
    private byte Status;

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public FuncionarioModel(String id, String nome, int nivel) {
        this.setTipoEntidade(EnumEntidadesDisponiveis.FUNCIONARIO);
        this.setNome(nome);
        this.setId(id);
        this.setNivel((byte) nivel);

    }
    public FuncionarioModel()
    {        this.setTipoEntidade(EnumEntidadesDisponiveis.FUNCIONARIO);

    }

    public byte getStatus() {
        return Status;
    }

    public void setStatus(byte status) {
        Status = status;
    }

    public String getTelefone() {
        return Telefone;
    }

    public EnumTipoUsuarios getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoUsuarios tipo) {
        this.tipo = tipo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNivel(byte nivel) {
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public byte getNivel() {
        return nivel;
    }
}
