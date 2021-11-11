package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;

public class ClienteModel  extends Entidade {

    private String nome;
    private String login;
    private String telefone;
    private int pontos;
    private byte status;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public ClienteModel(String id, String nome, String email, String telefone, int pontos){
        this.setTipoEntidade(EnumEntidadesDisponiveis.CLIENTE);
        setLogin(email);
        setTelefone(telefone);
        setPontos(pontos);

    }

    public ClienteModel(){this.setTipoEntidade(EnumEntidadesDisponiveis.CLIENTE);}
    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
