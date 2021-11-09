package br.com.fitcontrol.fitcontrol.DAO.MySQL;

import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConexaoMySQLTest {
    /**
     *  Testa a conexão do software com o DB MySQL
     */
    @Test
    public void test(){
        boolean testeOk=false;
        try {
            ConexaoMySQL.GetConexaoBD();
            testeOk=true;
        } catch (SQLException e) {
            e.printStackTrace();
            testeOk=false;
        }
        assertEquals(testeOk,true);
    }
    private static String CreateDBTeste()
    {
        String SQL="create database FitcontroldbTestes; " +
                "use FitcontroldbTestes; " +
                "create table tbUsuarios( "  +
                "id varchar(50) not null , " +
                "Nome varchar(50) unique not null, " +
                "Telefone varchar(50) unique not null, " +
                "Email varchar(50) unique not null, " +
                "Senha varchar(200) not null, " +
                "_Status int8, " +
                "Pontos int default 0, " +
                "NivelAcesso int8 not null, " +
                "Primary key (id) " +
                ");" +
                "create table tbCatracas( " +
                "id varchar(50) not null, " +
                "    Nome varchar(50) unique not null, " +
                "    Modelo varchar(50) unique not null, " +
                "    Tipo int8 not null, " +
                "    _Status int8, " +
                "    Primary key (id) " +
                ");" +
                "create table tbAcesso( " +
                "id varchar(50) not null, " +
                "    _Data datetime not null, " +
                "    tipo int8 not null," +
                "    idFuncionario varchar(50) not null, " +
                "    idCliente varchar(50) not null," +
                "    idCatraca varchar(50) not null," +
                "    foreign key (idFuncionario) references tbUsuarios (id), " +
                "    foreign key (idCliente) references tbUsuarios (id), " +
                "foreign key (idCatraca) references tbCatracas (id), " +
                "    Primary key (id) " +
                ");" +
                "create table tbRecompensa( " +
                "id varchar(50) not null, " +
                "    Titulo  varchar(50) unique not null, " +
                "    Pontos int not null, " +
                "    Primary key (id) " +
                ");" +
                "create table tbItensRecompensa( " +
                "id varchar(50) not null, " +
                "    Descricao  varchar(50) unique not null, " +
                "    Foto  varchar(50) unique not null, " +
                "    Primary key (id) " +
                ");" +
                "create table tbItensXRecompensa( " +
                "idItens varchar(50) not null , " +
                "    idRecompensa varchar(50) not null, " +
                "    foreign key (idItens) references tbItensRecompensa (id), " +
                "    foreign key (idRecompensa) references tbRecompensa (id) " +
                ");" +
                "create table tbRecompensaHist( " +
                "id varchar(50) not null, " +
                "    _Data datetime not null, " +
                "    Pontos int not null, " +
                "Titulo  varchar(50) unique not null, " +
                "    IdCliente varchar(50) not null, " +
                "    foreign key (IdCliente) references tbUsuarios (id), " +
                "Primary key (id) " +
                ");" +
                "create table tbPontuacaoHist( " +
                "id varchar(50) not null, " +
                "    Pontos int not null, " +
                "    idAcesso varchar(50), " +
                "    idCliente varchar(50) not null, " +
                "foreign key (idAcesso) references tbAcesso (id), " +
                "    foreign key (idCliente) references tbUsuarios (id), " +
                "    Primary key (id) " +
                ");" +
                "create table tbPagamentos( " +
                "id varchar(50) not null, " +
                "    _Data datetime not null, " +
                "    idCliente varchar(50) not null, " +
                "    idFuncionario varchar(50) not null, " +
                "    Valor real not null, " +
                "    foreign key (idFuncionario) references tbUsuarios (id), " +
                "    foreign key (idCliente) references tbUsuarios (id), " +
                "    Primary key (id) " +
                ");";

        return SQL;
    }
    /*
        Verifica se p DB de testes existe, se existir ele destroi e cria novamente conforme o banco de dados padrão
    * */
    public static boolean VerifyDBTest()
    {
        try {
            Connection conn= ConexaoMySQL.GetConexaoBD();
            String SQL = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'FitcontroldbTestes'";
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return true;

            else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
