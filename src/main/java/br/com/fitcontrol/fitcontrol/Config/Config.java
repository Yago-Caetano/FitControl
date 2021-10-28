package br.com.fitcontrol.fitcontrol.Config;

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

    private main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio enumTipoRepositorio;

    public main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio getTipoRepositorio() {
        return enumTipoRepositorio;
    }


    public void setDatabase(main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio enumTipoRepositorio) {
        this.enumTipoRepositorio = enumTipoRepositorio;
    }
}