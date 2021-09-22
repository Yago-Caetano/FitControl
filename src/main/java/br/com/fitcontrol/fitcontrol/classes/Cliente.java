package br.com.fitcontrol.fitcontrol.classes;

public class Cliente {
    private int id,pontos;
    private String nome,email,telefone;

    public Cliente(int id, String nome, String email, String telefone, int pontos){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.pontos = pontos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {this.pontos = pontos;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {this.email = email;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {this.nome = nome;}

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
