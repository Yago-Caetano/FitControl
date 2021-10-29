package br.com.fitcontrol.fitcontrol.dao;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;

import java.sql.*;

import java.util.ArrayList;

public abstract class MySQLDAO <E extends Entidade> extends PadraoDAO {
    protected final String STRING_CONEXAO = "jdbc:mysql://localhost:3306/fitcontroldb"; //"jdbc:mysql://localhost:3306/fitcontroldb";
    protected final String USUARIO = "root";
    protected final String SENHA = "123456";
    protected boolean Has_Status =false; // tem a coluna de status, delete não é permanente e consulta somente aos dados com status<>0
    private String tabela;

    public MySQLDAO(Class entityClass) {
        super(entityClass);
    }

    protected void setTabela(String value){
        tabela = value;
    }



    @Override
    public void Insert(Entidade entidade) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {
            String SQL = getInsertCommand((E) entidade);
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                PrepareStatementInsertUpdate((E) entidade,stmt);
                stmt.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void Update(Entidade entidade) {
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {
            String SQL = getUpdateCommand((E) entidade);
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                PrepareStatementInsertUpdate((E) entidade,stmt);
                stmt.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void Delete(Entidade entidade) {
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {
            String SQL;
            if (Has_Status)
                SQL = "Update " +getTabela() + " Set _Status = 0 where Id  = ? ";
            else
                SQL = "delete from " +this.tabela + " where id = ? ";
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
               stmt.setInt(1,entidade.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    @Override
    public E localiza (int codigo) throws SQLException {
        E entidade = null;
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA )) {
            String SQL;
            if (Has_Status)
                SQL= "select * from " + this.tabela + " where Id = ? and _Status>0";
            else
                SQL= "select * from " + this.tabela + " where Id = ?";
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                stmt.setInt(1, codigo);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()){
                        entidade = preencheEntidade(rs);
                    }
                }
            }
        }
        return entidade;
    }

    protected String getTabela() {return tabela;}
    protected abstract String getInsertCommand(E entidade);
    protected abstract String getUpdateCommand(E entidade);
    protected abstract void PrepareStatementInsertUpdate(E entidade,PreparedStatement stmt) throws SQLException;

    /*
    protected String getLocalizaCommand() {
        String campos = "";
        String chave = "";
        for (Field campo : entityClass.getDeclaredFields()) {
            if (campo.isAnnotationPresent(CampoNoBanco.class)) {
                CampoNoBanco anotacao = campo.getAnnotation(CampoNoBanco.class);
                if (anotacao.chave())
                    chave = anotacao.nome();
                campos += anotacao.nome() +",";
            }
        }
        if (campos.length() >0)
            campos = campos.substring(0, campos.length()-1);
        return "select "+ campos+ " from "+ tabela +" where "+chave +" = ?";
    }
*/
    protected abstract E preencheEntidade(ResultSet rs);

    @Override
    public ArrayList<E> lista() throws SQLException {
        ArrayList<E> entidades = new ArrayList();
        try (Connection conexao = DriverManager.getConnection(STRING_CONEXAO, USUARIO, SENHA)) {
            String SQL;
            if (Has_Status)
                SQL= "select * from " + this.tabela + " where _Status > 0";
            else
                SQL= "select * from " + this.tabela ;
            try (PreparedStatement stmt = conexao.prepareStatement(SQL)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()){
                        E entidade = preencheEntidade(rs);
                        entidades.add(entidade);
                    }
                }
            }
        }

        return entidades;
    }
}
