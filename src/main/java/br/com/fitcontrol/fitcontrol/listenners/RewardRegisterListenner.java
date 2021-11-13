package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Recompensa.RecompensaMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.RecompensaModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;

import java.sql.SQLException;

public class RewardRegisterListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) throws SQLException {

        try{
            RecompensaModel recompensa = new RecompensaModel();
            RecompensaMySQLDAO dao = new RecompensaMySQLDAO();
            recompensa = (RecompensaModel) context.getEntityData();
            dao.Insert(recompensa);

            SucessPopUpSingleton.getInstance().showPopUp("Cadastrado com sucesso");

            NavigationSingleton navigation = NavigationSingleton.getInstance();

            navigation.goBack();
        }
        catch (Exception e)
        {
            ErrorPopUpSingleton.getInstance().showError("Falha ao cadastrar");

        }

    }
}
