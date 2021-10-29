package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Recompensa.RecompensaMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.RecompensaModel;

import java.sql.SQLException;

public class RewardRegisterListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) throws SQLException {
        RecompensaModel recompensa = new RecompensaModel();
        RecompensaMySQLDAO dao = new RecompensaMySQLDAO();
        recompensa = (RecompensaModel) context.getEntityData();
        dao.Insert(recompensa);

    }
}
