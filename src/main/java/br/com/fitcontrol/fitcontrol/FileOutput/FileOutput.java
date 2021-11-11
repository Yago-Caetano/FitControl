package br.com.fitcontrol.fitcontrol.FileOutput;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileOutput {

    protected static final String CSV_FILE_NAME="Teste.csv";

    protected String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }
    public void CreateCSVFile(List<String[]> lista) throws IOException {

       File csvOutputFile = new File(CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            lista.stream().map(this::convertToCSV);
        }
        //assertTrue(csvOutputFile.exists());
    }
}
