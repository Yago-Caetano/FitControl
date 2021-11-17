package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Pagamento.PagamentosMySQLDAO;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;

public class PaymentDeleteListenner implements ISubscriber{

    @Override
    public void update(FitControlContext context) {
        try {
            PagamentosMySQLDAO payDao = new PagamentosMySQLDAO();

            payDao.Delete(context.getEntityData());
            SucessPopUpSingleton.getInstance().showPopUp("Removido com sucesso");
        } catch (Exception e) {
            ErrorPopUpSingleton.getInstance().showError("Falha ao remover");
            e.printStackTrace();
        }
    }
}
