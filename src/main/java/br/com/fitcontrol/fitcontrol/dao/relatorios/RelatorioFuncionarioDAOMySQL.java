package br.com.fitcontrol.fitcontrol.dao.relatorios;

import br.com.fitcontrol.fitcontrol.dao.RelatorioDAOMySQL;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioAlunoModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioFuncionarioModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RelatorioFuncionarioDAOMySQL <E extends RelatorioModel> extends RelatorioDAOMySQL {

    public RelatorioFuncionarioDAOMySQL(Date data1, Date data2) {
        super(data1,data2, RelatorioFuncionarioModel.class);
    }

    @Override
    protected String GetSQLRelatorio() {
        String SQL="Select tbA._Data as Data, tbU2.Nome as Funcionario,tbc.Nome as Catraca from tbAcesso tbA "+
                "inner join tbFuncionarios tbU2 on tbA.idFuncionario=tbU2.id inner join tbCatracas tbc on tbc.id=tbA.idCatraca where "+
                "tbA._Data >=? and tbA._Data <=? and tbU2.NivelAcesso=2 and tbA.idCliente is null";
        return SQL;
    }

    @Override
    protected List<String[]> PrepareData(List lista) {
        List<RelatorioFuncionarioModel> Data_Relatorio=lista;
        List<String[]> Data_preparada= new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        for(RelatorioFuncionarioModel data: Data_Relatorio)
        {
            Data_preparada.add(new String[]{dateFormat.format(data.getData()).toString(),
                    data.getFuncionario(),data.getCatraca()});
        }
        return Data_preparada;
    }

    @Override
    protected RelatorioModel PreencheRelatorio(ResultSet rs) {
        RelatorioFuncionarioModel relatorio = new RelatorioFuncionarioModel();
        try {
            relatorio.setData(rs.getDate("Data"));
            relatorio.setFuncionario(rs.getString("Funcionario"));
            relatorio.setCatraca(rs.getString("Catraca"));

        } catch (SQLException ex) {
            //Logger.getLogger(UsuarioMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (E)relatorio;
    }
}
