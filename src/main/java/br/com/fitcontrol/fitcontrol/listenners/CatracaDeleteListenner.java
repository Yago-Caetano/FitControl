package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Catraca.CatracaMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;

public class CatracaDeleteListenner implements ISubscriber
{
    @Override
    public void update(FitControlContext context) {
        CatracaModel catraca = new CatracaModel();
        CatracaMySQLDAO dao = new CatracaMySQLDAO();
        catraca = (CatracaModel) context.getEntityData();
        dao.Delete(catraca);
    }
}
