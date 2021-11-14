package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.popup.SucessPopUpSingleton;

import java.sql.SQLException;

public class EmployeeRegisterListenner implements ISubscriber{
    @Override
    public void update(FitControlContext context) throws SQLException {
        try{
            FuncionarioModel funcionario = new FuncionarioModel();
            FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();
            funcionario = (FuncionarioModel) context.getEntityData();
            dao.Insert(funcionario);

            SucessPopUpSingleton.getInstance().showPopUp("Funcionário cadastrado com sucesso");


            NavigationSingleton navigation = NavigationSingleton.getInstance();

            navigation.goBack();
        }
        catch(Exception e)
        {
            ErrorPopUpSingleton.getInstance().showError("Falha ao cadastrar funcionário");
        }


    }
}
