package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Catraca.CatracaMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;

import java.sql.SQLException;

public class CatracaRegisterListenner implements ISubscriber
{
    @Override
    public void update(FitControlContext context) throws SQLException {
        try{
            CatracaModel catraca = new CatracaModel();
            CatracaMySQLDAO dao = new CatracaMySQLDAO();
            catraca = (CatracaModel) context.getEntityData();
            dao.Insert(catraca);
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
