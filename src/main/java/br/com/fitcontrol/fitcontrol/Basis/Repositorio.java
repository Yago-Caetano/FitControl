package br.com.fitcontrol.fitcontrol.Basis;

import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoRelatorio;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioModel;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Repositorio {
    public abstract Entidade localiza(String codigo, EnumEntidadesDisponiveis tipoEntidade);
    public abstract ArrayList<Entidade> GetAll(EnumEntidadesDisponiveis tipoEntidade) throws SQLException;
    public abstract Entidade login(String login,String Senha);
    public abstract void Insert(Entidade entidade, EnumEntidadesDisponiveis tipoEntidade) throws SQLException;
    public abstract void Update(Entidade entidade, EnumEntidadesDisponiveis tipoEntidade);
    public abstract void Delete(Entidade entidade, EnumEntidadesDisponiveis tipoEntidade);
    public abstract void  GetRelatorio (Date data1,Date data2, EnumTipoRelatorio tiporelatorio);
}