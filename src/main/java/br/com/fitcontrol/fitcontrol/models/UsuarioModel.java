package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;

public class UsuarioModel  extends Entidade {

    private EnumTipoUsuarios tipo;
    private String nome;
    private byte nivel;
    private String login;
    private String senha;
    private String Telefone;
    private int pontos;
    private byte Status;

    public byte getStatus() {
        return Status;
    }

    public void setStatus(byte status) {
        Status = status;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }
    public UsuarioModel()
    {
        this.setTipoEntidade(EnumEntidadesDisponiveis.USUARIO);

    }
    public UsuarioModel(String id, String nome, byte nivel) {
        super.setId(id);
        this.nome = nome;
        this.nivel = nivel;
        this.setTipoEntidade(EnumEntidadesDisponiveis.USUARIO);

    }
    public UsuarioModel ConvertToFuncOrCliente()
    {
        UsuarioModel user=null;
        if (this.nivel>1)
        {
            FuncionarioModel f = new FuncionarioModel(this.getId(),this.nome,this.nivel);
            f.setLogin(this.login);
            f.setSenha(this.senha);
            f.setTelefone(this.Telefone);
            f.setStatus(this.Status);
            user=f;
        }
        else
        {
            ClienteModel c = new ClienteModel(this.getId(),this.nome,this.login,this.Telefone,this.pontos);
            c.setSenha(this.senha);
            c.setStatus(this.Status);
            user=c;
        }
        return user;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
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
