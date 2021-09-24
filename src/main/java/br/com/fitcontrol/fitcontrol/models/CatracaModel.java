package br.com.fitcontrol.fitcontrol.models;

public class CatracaModel {

    private int id;
    private String modelo;

    public CatracaModel(int id, String modelo) {
        this.id = id;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
