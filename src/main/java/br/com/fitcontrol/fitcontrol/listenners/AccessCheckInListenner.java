package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlContext;

public class AccessCheckInListenner implements ISubscriber{


    @Override
    public void update(FitControlContext context) {

        //EXEMPLO PARA SALVAR DADOS
        try {
            Repositorio r = FabricaRepositorio.Fabrica();

            r.Insert(context.getEntityData(), context.getEntityData().getTipoEntidade());

            System.out.println("ENTROU PELA CATRACA");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
