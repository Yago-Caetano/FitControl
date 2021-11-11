package br.com.fitcontrol.fitcontrol.dao;

import br.com.fitcontrol.fitcontrol.models.relatorios.RelatorioModel;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class RelatorioDAO <E extends RelatorioModel> {

    protected  Date data1;
    protected  Date data2;

    protected final String CSV_FILE_NAME="Teste.csv";

    protected Class<E> entityClass;

    public RelatorioDAO(Date data1, Date data2, Class entityClass) {
        this.data1=data1;
        this.data2=data2;
        this.entityClass=entityClass;
    }

    public abstract void GetRelatorio();

    protected String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }
    protected void CreateCSVFile(List<String[]> lista) throws IOException {

        File csvOutputFile = new File(CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            lista.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
        //assertTrue(csvOutputFile.exists());
    }
}
