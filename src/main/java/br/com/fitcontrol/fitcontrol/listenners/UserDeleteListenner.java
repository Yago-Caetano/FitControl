package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;

public class UserDeleteListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) {
        try{
            ClienteModel cliente = new ClienteModel();
            ClienteMySQLDAO dao = new ClienteMySQLDAO();
            cliente = (ClienteModel) context.getEntityData();
            dao.Delete(cliente);

            SucessPopUpSingleton.getInstance().showPopUp("Removido com sucesso");
        }
        catch(Exception e)
        {
            ErrorPopUpSingleton.getInstance().showError("Falha ao remover");
        }

    }
}
