package br.com.fitcontrol.fitcontrol.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    protected static final String STRING_CONEXAO = "jdbc:mysql://127.0.0.1:3306/fitcontroldb"; //"jdbc:mysql://localhost:3306/fitcontroldb";
    protected static final String USUARIO = System.getenv("FIT_CONTROL_DB_USER");
    protected static final String SENHA = System.getenv("FIT_CONTROL_DB_PSW");
    public static Connection GetConexaoBD() throws SQLException {
        return DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA );
    }

    public static Connection GetConexaoBDTeste() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fitcontroldbTestes", USUARIO, SENHA );
    }
}
