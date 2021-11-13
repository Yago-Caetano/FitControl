package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Recompensa.RecompensaMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.RecompensaModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.sql.SQLException;

public class RewardRegisterListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) throws SQLException {

        try{
            RecompensaModel recompensa = new RecompensaModel();
            RecompensaMySQLDAO dao = new RecompensaMySQLDAO();
            recompensa = (RecompensaModel) context.getEntityData();
            dao.Insert(recompensa);

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
        catch (Exception e)
        {
            ErrorPopUpSingleton.getInstance().showError("Falha ao cadastrar");

        }

    }
}
