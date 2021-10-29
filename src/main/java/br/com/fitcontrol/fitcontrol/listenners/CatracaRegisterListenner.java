package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Catraca.CatracaMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;

import java.sql.SQLException;

public class CatracaRegisterListenner implements ISubscriber
{
    @Override
    public void update(FitControlContext context) throws SQLException {
        CatracaModel catraca = new CatracaModel();
        CatracaMySQLDAO dao = new CatracaMySQLDAO();
        catraca = (CatracaModel) context.getEntityData();
        dao.Insert(catraca);
    }
}
