package br.com.fitcontrol.fitcontrol.Acesso;

import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;

import java.util.Stack;

public class FuncionarioLogadoSingleton {

    public Boolean userSigned() {
        return signed;
    }

    private Boolean signed = false;

    private static FuncionarioLogadoSingleton instance = new FuncionarioLogadoSingleton();

    private FuncionarioLogadoSingleton() {}

    public static FuncionarioLogadoSingleton getInstance()
    {
        return instance;
    }

    private FuncionarioModel EmployeeSigned;

    public FuncionarioModel getEmployeeSigned() {
        return EmployeeSigned;
    }

    public void setEmployeeSigned(FuncionarioModel employeeSigned) {
        EmployeeSigned = employeeSigned;
        signed = true;
    }

    public void signedOut()
    {
        EmployeeSigned = null;
        signed = false;
    }


}
