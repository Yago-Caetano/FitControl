package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;

import java.sql.SQLException;

public class UserRegisterListenner implements ISubscriber{

    @Override
    public void update(FitControlContext context) throws SQLException {

        try{
            ClienteModel cliente = new ClienteModel();
            ClienteMySQLDAO dao = new ClienteMySQLDAO();
            cliente = (ClienteModel) context.getEntityData();

            dao.Insert(cliente);

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
