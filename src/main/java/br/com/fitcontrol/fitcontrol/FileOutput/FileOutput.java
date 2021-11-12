package br.com.fitcontrol.fitcontrol.FileOutput;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileOutput {

    protected String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }

    public void CreateCSVFile(List<String[]> lista,String path) throws IOException {
        //Create a file chooser
        File csvOutputFile = new File(path);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            lista.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }
}
