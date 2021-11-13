package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.Basis.Repositorio;
import br.com.fitcontrol.fitcontrol.Fabricas.FabricaRepositorio;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Pagamento.PagamentosMySQLDAO;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;

public class PaymenteUpdateListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) {
        try {

            PagamentosMySQLDAO PagDAO = new PagamentosMySQLDAO();
            PagDAO.Update(context.getEntityData());

            SucessPopUpSingleton.getInstance().showPopUp("Atualizado com sucesso!");
            NavigationSingleton.getInstance().goBack();

        } catch (Exception e) {

            ErrorPopUpSingleton.getInstance().showError("Falha ao Atualizar");
        }
    }
}
