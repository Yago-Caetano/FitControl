package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;

public class UserDeleteListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) {
        System.out.println(context.getClienteData().getNome() + " Foi deletado");
    }
}
