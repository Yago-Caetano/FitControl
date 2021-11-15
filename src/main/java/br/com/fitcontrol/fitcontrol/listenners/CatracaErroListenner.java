package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.models.CatracaErro;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;

public class CatracaErroListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) {
        CatracaErro erro = (CatracaErro) context.getEntityData();
        ErrorPopUpSingleton.getInstance().showError("Catraca erro nº: " + erro.getCodErro());
    }
}
