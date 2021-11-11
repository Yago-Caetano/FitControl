package br.com.fitcontrol.fitcontrol.Fabricas;

import br.com.fitcontrol.fitcontrol.Enums.EnumTipoRelatorio;
import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.dao.RelatorioDAO;
import br.com.fitcontrol.fitcontrol.dao.relatorios.RelatorioAlunoDAOMySQL;
import br.com.fitcontrol.fitcontrol.dao.relatorios.RelatorioFuncionarioDAOMySQL;
import br.com.fitcontrol.fitcontrol.dao.relatorios.RelatorioPagamentoDAOMySQL;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioAlunoModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioFuncionarioModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioPagamentoModel;

import java.sql.*;

public class FabricaRelatorios {

    private static java.sql.Date data1;
    private static java.sql.Date data2;

    public static RelatorioDAO Fabrica(Date primeiraData,Date segundaData,EnumTipoRelatorio tipoRelatorio, main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio repositorio )
    {
        data1=primeiraData;
        data2=segundaData;
        switch (repositorio)
        {
            case MYSQL:
                return MontaRelatorioMySQL(tipoRelatorio);
        }

        return null;

    }
    private static RelatorioDAO MontaRelatorioMySQL(EnumTipoRelatorio tipoRelatorio)
    {
        switch (tipoRelatorio)
        {
            case ALUNO:
                return  new RelatorioAlunoDAOMySQL(data1,data2);
            case FUNCIONARIO:
                return new RelatorioFuncionarioDAOMySQL(data1,data2);
            case PAGAMENTO:
                return new RelatorioPagamentoDAOMySQL(data1,data2);
            default:
                return null;
        }
    }


}
