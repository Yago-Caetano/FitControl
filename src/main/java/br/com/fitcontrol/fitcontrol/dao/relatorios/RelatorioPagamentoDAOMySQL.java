package br.com.fitcontrol.fitcontrol.dao.relatorios;

import br.com.fitcontrol.fitcontrol.dao.RelatorioDAOMySQL;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioFuncionarioModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioPagamentoModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RelatorioPagamentoDAOMySQL <E extends RelatorioModel> extends RelatorioDAOMySQL {

    public RelatorioPagamentoDAOMySQL(Date data1, Date data2) {
        super(data1,data2, RelatorioPagamentoModel.class);
    }

    @Override
    protected String GetSQLRelatorio() {
        String SQL="Select tbP._Data as Data,tbC.Nome as Cliente,tbF.Nome as Funcionario, tbP.Valor as Valor "+
                "from tbPagamentos tbP inner join tbClientes tbC on tbC.id=tbP.idCliente "+
                "inner join tbFuncionarios tbF on tbF.id=tbP.idFuncionario "+
                "where tbA._Data >=? and tbA._Data <=?";
        return SQL;
    }

    @Override
    protected List<String[]> PrepareData(List lista) {
        List<RelatorioPagamentoModel> Data_Relatorio=lista;
        List<String[]> Data_preparada= new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        for(RelatorioPagamentoModel data: Data_Relatorio)
        {
            Data_preparada.add(new String[]{dateFormat.format(data.getData()).toString(),
                    data.getCliente(),data.getFuncionario(),Double.toString(data.getValor())});
        }
        return Data_preparada;
    }

    @Override
    protected RelatorioModel PreencheRelatorio(ResultSet rs) {
        RelatorioPagamentoModel relatorio = new RelatorioPagamentoModel();
        try {
            relatorio.setData(rs.getDate("Data"));
            relatorio.setCliente(rs.getString("Funcionario"));
            relatorio.setFuncionario(rs.getString("Cliente"));
            relatorio.setValor(rs.getDouble("Valor"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)relatorio;
    }
}
