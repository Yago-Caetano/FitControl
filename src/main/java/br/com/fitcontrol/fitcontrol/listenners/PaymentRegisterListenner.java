package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;

public class PaymentRegisterListenner implements ISubscriber {
    @Override
    public void update(FitControlContext context) {

        try {
            Repositorio r = FabricaRepositorio.Fabrica();

            r.Insert(context.getEntityData(), context.getEntityData().getTipoEntidade());

            SucessPopUpSingleton.getInstance().showPopUp("Cadastrado com sucesso");

            NavigationSingleton navigation = NavigationSingleton.getInstance();

            navigation.goBack();

        } catch (Exception e) {

        }
    }
}
