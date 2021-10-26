package br.com.fitcontrol.fitcontrol.Config;
import main.java.br.com.fitcontrol.fitcontrol.Enums.TipoRepositorio;
public class Config {
    private static Config uniqueInstance;

    // Isso é um Singleton - Somente utilize o método getInstance para
    // acessar o objeto! => Construtor privado
    private Config() {
    }

    public static synchronized Config getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Config();

        return uniqueInstance;
    }

    private TipoRepositorio tipoRepositorio;

    public TipoRepositorio getTipoRepositorio() {
        return tipoRepositorio;
    }


    public void setDatabase(TipoRepositorio tipoRepositorio) {
        this.tipoRepositorio = tipoRepositorio;
    }
}