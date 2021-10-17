package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;

public class UserRegisterListenner implements ISubscriber{

    @Override
    public void update(FitControlContext context) {
        //DAO insere Client?
        System.out.println("Aluno inserido");
    }
}
