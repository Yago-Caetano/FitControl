package br.com.fitcontrol.fitcontrol.dao.relatorios;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.MySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.RelatorioDAOMySQL;
import br.com.fitcontrol.fitcontrol.models.AcessoModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioAlunoModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RelatorioAlunoDAOMySQL <E extends RelatorioModel> extends RelatorioDAOMySQL {

    public RelatorioAlunoDAOMySQL(Date data1,Date data2) {
        super(data1,data2,RelatorioAlunoModel.class);
    }

    @Override
    protected String GetSQLRelatorio() {

        String SQL="Select tbA._Data as Data,tbU.Nome as Cliente,tbU2.Nome as Funcionario,tbc.Nome as Catraca from tbAcesso tbA "+
                "inner join tbClientes tbU on tbA.idCliente=tbU.id "+
                "inner join tbFuncionarios tbU2 on tbA.idFuncionario=tbU2.id inner join tbCatracas tbc on tbc.id=tbA.idCatraca where "+
                "tbA._Data >=? and tbA._Data <=?";
        return SQL;
    }

    @Override
    protected List<String[]> PrepareData(List lista) {
        List<RelatorioAlunoModel> Data_Relatorio=lista;
        List<String[]> Data_preparada= new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        for(RelatorioAlunoModel data: Data_Relatorio)
        {
            Data_preparada.add(new String[]{dateFormat.format(data.getData()).toString(),
                    data.getCliente(),data.getFuncionario(),data.getCatraca()});
        }
        return Data_preparada;
    }

    @Override
    protected RelatorioModel PreencheRelatorio(ResultSet rs) {
        RelatorioAlunoModel relatorio = new RelatorioAlunoModel();
        try {
            relatorio.setData(rs.getDate("Data"));
            relatorio.setFuncionario(rs.getString("Funcionario"));
            relatorio.setCliente(rs.getString("Cliente"));
            relatorio.setCatraca(rs.getString("Catraca"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)relatorio;
    }
}
