package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlContext;

public class AcessCheckOutListenner implements ISubscriber{

    //@Override
    //public void update(FitControlContext context) {        System.out.println("SAIU PELA CATRACA");    }

    @Override
    public void update(FitControlContext context) {

        try {
            Repositorio r = FabricaRepositorio.Fabrica();

            r.Insert(context.getEntityData(), context.getEntityData().getTipoEntidade());

            System.out.println("SAIU PELA CATRACA");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
