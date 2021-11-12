package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;

public class AccessCheckInListenner implements ISubscriber{


    @Override
    public void update(FitControlContext context) {

        //EXEMPLO PARA SALVAR DADOS
        try {
            Repositorio r = FabricaRepositorio.Fabrica();

            r.Insert(context.getEntityData(), context.getEntityData().getTipoEntidade());

            CatracaModel catraca = new CatracaModel();
            catraca = (CatracaModel) context.getEntityData();
            System.out.println("ENTROU PELA CATRACA" + catraca.getModelo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
