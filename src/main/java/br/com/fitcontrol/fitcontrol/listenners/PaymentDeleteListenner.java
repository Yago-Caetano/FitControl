package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlContext;

public class PaymentDeleteListenner implements ISubscriber{

    @Override
    public void update(FitControlContext context) {
        try {
            Repositorio r = FabricaRepositorio.Fabrica();

            r.Insert(context.getEntityData(), context.getEntityData().getTipoEntidade());

            System.out.println("EXCLUIU UM PAGAMENTO");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
