package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;

public class AccessCheckInListenner implements ISubscriber{


    @Override
    public void update(FitControlContext context) {
        System.out.println("ENTROU PELA CATRACA");
    }
}
