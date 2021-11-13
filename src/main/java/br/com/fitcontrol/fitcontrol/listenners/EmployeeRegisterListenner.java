package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.sql.SQLException;

public class EmployeeRegisterListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) throws SQLException {
        try{
            FuncionarioModel funcionario = new FuncionarioModel();
            FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();
            funcionario = (FuncionarioModel) context.getEntityData();
            dao.Insert(funcionario);

            SucessPopUpSingleton.getInstance().showPopUp("Cadastrado com sucesso");


            NavigationSingleton navigation = NavigationSingleton.getInstance();

            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                    navigation.getStage().setScene(scene);
                }
            });
        }
        catch(Exception e)
        {

        }


    }
}
