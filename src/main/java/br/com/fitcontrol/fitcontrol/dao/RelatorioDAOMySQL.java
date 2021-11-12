package br.com.fitcontrol.fitcontrol.dao;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.FileOutput.FileOutput;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioAlunoModel;
import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioModel;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class  RelatorioDAOMySQL <E extends RelatorioModel> extends RelatorioDAO{

    public RelatorioDAOMySQL(Date data1, Date data2,Class entityClass) {
        super(data1,data2,entityClass);
    }

    protected abstract String GetSQLRelatorio();

    protected  List<E> GetRelatorioFromMySQL()
    {
        List<E> relatorio = new ArrayList<>();
        try {
            Connection conn = ConexaoMySQL.GetConexaoBD();
            PreparedStatement stmt = conn.prepareStatement(GetSQLRelatorio());
            stmt.setDate(1,data1);
            stmt.setDate(2,data2);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                E Data= PreencheRelatorio(rs);
                relatorio.add(Data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatorio;
    }
    /*
    Converte uma lista de um relatorio para uma lista de array de string para preparar a conversão para CSV
    * */
    protected abstract List<String[]> PrepareData(List<E> lista);

    protected  abstract E PreencheRelatorio(ResultSet rs);

    @Override
    public void GetRelatorio(String path) {

        List<E> relatorio=GetRelatorioFromMySQL();
        List<String[]>ConvertedData=PrepareData(relatorio);
        try {
            FileOutput file = new FileOutput();
            file.CreateCSVFile(ConvertedData,path);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
