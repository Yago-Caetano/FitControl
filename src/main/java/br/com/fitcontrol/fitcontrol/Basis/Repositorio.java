package br.com.fitcontrol.fitcontrol.Basis;

import br.com.fitcontrol.fitcontrol.Enums.EntidadesDisponiveis;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Repositorio {
    public abstract Entidade localiza(int codigo, EntidadesDisponiveis tipoEntidade);
    public abstract ArrayList<Entidade> GetAll(EntidadesDisponiveis tipoEntidade) throws SQLException;
    public abstract Entidade login(String login,String Senha, EntidadesDisponiveis tipoEntidade);
    public abstract void Insert(Entidade entidade,EntidadesDisponiveis tipoEntidade);
    public abstract void Update(Entidade entidade,EntidadesDisponiveis tipoEntidade);
    public abstract void Delete(Entidade entidade,EntidadesDisponiveis tipoEntidade);
}