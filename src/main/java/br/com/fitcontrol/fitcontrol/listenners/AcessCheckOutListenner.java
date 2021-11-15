package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Acesso.AcessoMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.AcessoModel;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;

public class AcessCheckOutListenner implements ISubscriber{

    //@Override
    //public void update(FitControlContext context) {        System.out.println("SAIU PELA CATRACA");    }

    @Override
    public void update(FitControlContext context) {


        try {
            AcessoModel acesso = (AcessoModel) context.getEntityData();
            AcessoMySQLDAO dao = new AcessoMySQLDAO();

            dao.Insert(acesso);
            SucessPopUpSingleton.getInstance().showPopUp(acesso.getIdCliente() + "\r\nAcesso Autorizado");
        } catch (Exception e) {
            ErrorPopUpSingleton.getInstance().showError("Acesso negado");
        }
    }
}
