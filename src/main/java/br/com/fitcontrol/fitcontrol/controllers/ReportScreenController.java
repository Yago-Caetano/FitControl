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
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ReportScreenController implements Initializable {

    java.sql.Date data1;
    java.sql.Date data2;

    private NavigationSingleton navigation;
    @FXML
    private Button voltar;
    @FXML
    private Button Staffbtn;
    @FXML
    private Button PagamentoBtn;
    @FXML
    private Button AlunoBtn;
    @FXML
    private TextField Data1txt;
    @FXML
    private TextField Data2txt;
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
                r = FabricaRepositorio.Fabrica();
                r.GetRelatorio(data1,data2, EnumTipoRelatorio.ALUNO);

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
                r = FabricaRepositorio.Fabrica();
                r.GetRelatorio(data1,data2, EnumTipoRelatorio.FUNCIONARIO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    protected void PagamentoClicked() {
        if(ValidaDatas())
        {
            Repositorio r = null;
            try {
                r = FabricaRepositorio.Fabrica();
                r.GetRelatorio(data1,data2, EnumTipoRelatorio.PAGAMENTO);

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
        if(Data1txt.getText().length()==0 || Data2txt.getText().length()==0 )
        {
            return false;
        }
        else
        {

            DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            try {
                data1 = new java.sql.Date(fmt.parse(Data1txt.getText()).getTime());
                data2 = new java.sql.Date(fmt.parse(Data2txt.getText()).getTime());

                return true;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return false;

    }


}
