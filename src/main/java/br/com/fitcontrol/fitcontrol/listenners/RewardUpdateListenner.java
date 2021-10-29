package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.models.RecompensaModel;

public class RewardUpdateListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) {
        RecompensaModel recompensa = new RecompensaModel();
        recompensa = (RecompensaModel) context.getEntityData();
        System.out.println("Recompensa " + recompensa.getDescricao() + " Atualizada");
    }
}
