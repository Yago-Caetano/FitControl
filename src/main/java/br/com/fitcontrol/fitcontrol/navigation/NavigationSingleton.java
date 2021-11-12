package br.com.fitcontrol.fitcontrol.navigation;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.error.ErrorPopUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Queue;
import java.util.Stack;

public  class NavigationSingleton {

    public static final int MAIN_SCREEN = 0;
    public static final int CATRACA_SCREEN = 1;
    public static final int STAFF_SCREEN = 2;
    public static final int PAYMENT_SCREEN = 3;
    public static final int CLIENTS_SCREEN = 4;
    public static final int REWARDS_SCREEN = 5;
    public static final int REPORT_SCREEN = 6;
    public static final int CLIENTS_EDIT_SCREEN = 7;
    public static final int EMPLOYEE_EDIT_SCREEN = 8;
    public static final int PAYMENT_EDIT_SCREEN = 9;
    public static final int LOGIN_SCREEN = 10;
    public static final int CATRACA_EDIT_SCREEN = 11;
    public static final int REWARDS_EDIT_SCREEN = 12;


    private Stack<String> telas;
    private String telaAtual;
    private Stage stagePrincipal;

    private static NavigationSingleton instance = new NavigationSingleton();

    private NavigationSingleton()
    {
        telas = new Stack<String>();
    }

    public static NavigationSingleton getInstance()
    {
        return instance;
    }

    public void setStage(Stage stage)
    {
        stagePrincipal = stage;
    }

    public void setRootScreen(int screenCode)
    {
        setCurrentScreen(screenCode);
    }

    public Stage getStage(){return  stagePrincipal;}


    public void goBack(iNavCallback navigationCallback) throws Exception {
        if(telas.size()>1)
        {
            telas.pop();
            telaAtual = telas.pop();
            telas.add(telaAtual);
            navigationCallback.navigateCb(telaAtual);

        }
    }

    private void setCurrentScreen(int screenId)
    {
        String resourceName = "";

        switch (screenId)
        {
            case MAIN_SCREEN:
                resourceName = "main-screen.fxml";
                break;

            case CATRACA_SCREEN:
                resourceName = "catracas-screen.fxml";
                break;

            case STAFF_SCREEN:
                resourceName = "employees-screen.fxml";
                break;

            case PAYMENT_SCREEN:
                resourceName = "payments-screen.fxml";
                break;

            case CLIENTS_SCREEN:
                resourceName = "clients-screen.fxml";
                break;

            case REWARDS_SCREEN:
                resourceName = "rewards-screen.fxml";
                break;

            case REPORT_SCREEN:
                resourceName = "reports-screen.fxml";
                break;

            case CLIENTS_EDIT_SCREEN:
                resourceName = "client-edit-screen.fxml";
                break;

            case EMPLOYEE_EDIT_SCREEN:
                resourceName = "employee-edit-screen.fxml";
                break;

            case PAYMENT_EDIT_SCREEN:
                resourceName = "payment-edit-screen.fxml";
                break;
            case LOGIN_SCREEN:
                resourceName = "login-screen.fxml";
                break;
            case CATRACA_EDIT_SCREEN:
                resourceName = "catraca-edit-screen.fxml";
                break;
            case REWARDS_EDIT_SCREEN:
                resourceName = "rewards-edit-screen.fxml";
                break;
        }

        telas.add(resourceName);

        telaAtual = resourceName;

    }

    public void navigate(int screenId,iNavCallback navigationCallback)
    {
        try{
            setCurrentScreen(screenId);
            navigationCallback.navigateCb(telaAtual);

        }
        catch(Exception e)
        {

        }

    }

    public void showErrorMessage(String Message)
    {
        new ErrorPopUp(stagePrincipal).showError(Message);
    }
}
