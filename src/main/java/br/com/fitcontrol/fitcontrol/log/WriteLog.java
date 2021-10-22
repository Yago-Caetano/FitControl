package br.com.fitcontrol.fitcontrol.log;

import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Stack;

public class WriteLog extends  Thread{

    private final String  Caminho = "Log.txt";

    String texto;
    public  WriteLog(String texto)
    {
       this.texto = texto;
    }

    @Override
    public void run() {
        Calendar c = Calendar.getInstance();
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String TextAux = "";
        TextAux = sdf.format(c.getTime()) + " - " + texto + "\n";
        write(TextAux, Caminho);
    }

    public static void write(String s, String Caminho) {

        try(FileWriter fw = new FileWriter(Caminho, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(s);
            //more code
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo de dados");
        }
    }
}
