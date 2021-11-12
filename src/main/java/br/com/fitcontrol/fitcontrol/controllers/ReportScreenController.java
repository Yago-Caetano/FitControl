package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoRelatorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRelatorios;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;

public class ReportScreenController implements Initializable {

    java.sql.Date data1;
    java.sql.Date data2;
    String path;
    String pattern = "dd/MM/yyyy";

    private NavigationSingleton navigation;
    @FXML
    private Button voltar;
    @FXML
    private Button Staffbtn;
    @FXML
    private ImageView PagamentoBtn;
    @FXML
    private Button AlunoBtn;
    @FXML
    private DatePicker Data1txt;
    @FXML
    private DatePicker Data2txt;
    @FXML
    protected void voltarClicked() {
        try {
                navigation.goBack(new iNavCallback() {
                    @Override
                    public void navigateCb(String screenName) throws Exception {
                        FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                        navigation.getStage().setScene(scene);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
        }
    }
    @FXML
    protected void AlunoClicked() {
        if(ValidaDatas())
        {
            Repositorio r = null;
            try {
                if(OpenFileDialog())
                {
                    r = FabricaRepositorio.Fabrica();
                    r.GetRelatorio(data1,data2, EnumTipoRelatorio.ALUNO,path);
                }
                else
                    NavigationSingleton.getInstance().showErrorMessage("Operação cancelada!");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    protected void StaffClicked() {
        if(ValidaDatas())
        {
            Repositorio r = null;
            try {
                if(OpenFileDialog())
                {
                    r = FabricaRepositorio.Fabrica();
                    r.GetRelatorio(data1,data2, EnumTipoRelatorio.FUNCIONARIO,path);
                }
                else
                    NavigationSingleton.getInstance().showErrorMessage("Operação cancelada!");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private boolean OpenFileDialog()
    {
        //Create a file chooser
        final JFileChooser fc = new JFileChooser();

        fc.setCurrentDirectory(new java.io.File("."));
        fc.setDialogTitle("Escolha o o diretorio para salvar");
        int userSelection = fc.showSaveDialog(null);
        if (userSelection==JFileChooser.APPROVE_OPTION)
        {
            path=fc.getSelectedFile().getPath();
            if (!path .endsWith(".csv"))
            {
                path += ".csv";
                return true;
            }
            
        }

        return false;
    }
    @FXML
    protected void pagamentoClicked() {
        if(ValidaDatas())
        {
            Repositorio r = null;
            try {

                if(OpenFileDialog())
                {
                    r = FabricaRepositorio.Fabrica();
                    r.GetRelatorio(data1,data2, EnumTipoRelatorio.PAGAMENTO,path);
                }
                else
                    NavigationSingleton.getInstance().showErrorMessage("Operação cancelada!");



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void InitilizaeDatePicker(DatePicker datePicker)
    {
        datePicker.setPromptText(pattern.toLowerCase());
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);



            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }


    public void initialize(URL location, ResourceBundle resources){
        InitilizaeDatePicker(Data1txt);
        InitilizaeDatePicker(Data2txt);
        navigation = NavigationSingleton.getInstance();
    }

    private boolean ValidaDatas()
    {
        if(Data1txt.getValue()== null|| Data2txt.getValue()== null )
        {
            NavigationSingleton.getInstance().showErrorMessage("Data inválida!");
            return false;
        }
        else
        {

            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            try {
                data1 = new java.sql.Date(fmt.parse(Data1txt.getValue().toString()).getTime());
                data2 = new java.sql.Date(fmt.parse((Data2txt.getValue().toString())).getTime());

                return true;

            } catch (ParseException e) {
                NavigationSingleton.getInstance().showErrorMessage("Data inválida!");
            }
        }

        return false;

    }


}
