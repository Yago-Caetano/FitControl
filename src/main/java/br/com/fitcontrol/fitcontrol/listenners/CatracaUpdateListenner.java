package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;

public class CatracaUpdateListenner implements ISubscriber
{
    @Override
    public void update(FitControlContext context) {
        CatracaModel catraca = new CatracaModel();
        catraca = (CatracaModel) context.getEntityData();
        System.out.println("Catraca ID:" + (catraca.getId()) + " Atualizada");
    }
}
