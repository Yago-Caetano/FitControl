package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoRelatorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javax.swing.JFileChooser;

public class ReportScreenController extends PadrãoController implements Initializable {

    java.sql.Date data1;
    java.sql.Date data2;
    String path;

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
    private DatePicker Data_End_txt,Data_Start_txt;

    @FXML
    protected void voltarClicked() {
        try {
                navigation.goBack();
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
                {

                }

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
                {

                }

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
            return true;
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
                {

                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void initialize(URL location, ResourceBundle resources){
        navigation = NavigationSingleton.getInstance();
    }

    private boolean ValidaDatas()
    {

        if(Data_Start_txt.getValue() == null || Data_End_txt.getValue() == null )
        {
            return false;
        }
        else
        {

            DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            try {
                data1 = Date.valueOf(Data_Start_txt.getValue());
                data2 = Date.valueOf(Data_End_txt.getValue());

                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;

    }


    @Override
    protected void PreviousScreenDataReceived() {

    }
}
