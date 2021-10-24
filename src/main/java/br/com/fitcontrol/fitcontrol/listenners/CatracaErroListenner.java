package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;

public class CatracaErroListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) {
        System.out.println("ERRO NA CATRACA");
    }
}
