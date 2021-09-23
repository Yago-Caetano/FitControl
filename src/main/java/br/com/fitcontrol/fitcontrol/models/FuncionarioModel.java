package br.com.fitcontrol.fitcontrol.models;

public class FuncionarioModel {
    private int id;
    private String nome;
    private int nivel;

    public FuncionarioModel(int id, String nome, int nivel) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }
}
