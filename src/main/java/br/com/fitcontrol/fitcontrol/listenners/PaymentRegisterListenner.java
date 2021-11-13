package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class PaymentRegisterListenner implements ISubscriber {
    @Override
    public void update(FitControlContext context) {

        try {
            Repositorio r = FabricaRepositorio.Fabrica();

            r.Insert(context.getEntityData(), context.getEntityData().getTipoEntidade());

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

        } catch (Exception e) {

        }
    }
}
