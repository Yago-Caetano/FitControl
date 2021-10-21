package br.com.fitcontrol.fitcontrol.dao;

import main.java.br.com.fitcontrol.fitcontrol.Basis.Entidade;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class PadraoDAO <E extends Entidade> {

    protected Class<E> entityClass;

    public PadraoDAO(Class entityClass) {

    }

    public void DAO(Class<E> entityClass){
        this.entityClass = entityClass;
    }

    public abstract E seleciona(int id);
    public abstract E localiza(String codigo) throws SQLException;
    public abstract ArrayList<E> lista() throws SQLException;

    protected E getInstanceOfE()
    {
        try
        {
            return entityClass.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e)
        {
            // Oops, no default constructor
            throw new RuntimeException(e);
        }
    }

}
