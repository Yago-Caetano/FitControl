package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;

import java.sql.SQLException;

public class EmployeeRegisterListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) throws SQLException {
        FuncionarioModel funcionario = new FuncionarioModel();
        FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();
        funcionario = (FuncionarioModel) context.getEntityData();
        dao.Insert(funcionario);

    }
}
