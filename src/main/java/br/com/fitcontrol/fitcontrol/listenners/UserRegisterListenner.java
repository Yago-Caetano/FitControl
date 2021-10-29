package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;

import java.sql.SQLException;

public class UserRegisterListenner implements ISubscriber{

    @Override
    public void update(FitControlContext context) throws SQLException {
        ClienteModel cliente = new ClienteModel();
        ClienteMySQLDAO dao = new ClienteMySQLDAO();
        cliente = (ClienteModel) context.getEntityData();
        cliente.setSenha("123");
        cliente.setNivel((byte) 1);
        dao.Insert(cliente);

    }
}
