package br.com.fitcontrol.fitcontrol.navigation;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public Stage getStage(){return  stagePrincipal;}

    public void navigate(int screenId,iNavCallback cb)
    {
        try{
            String resourceName = "";

            switch (screenId)
            {
                case MAIN_SCREEN:
                    resourceName = "main-screen.fxml";
                    break;

                case CATRACA_SCREEN:
                    resourceName = "rewards-screen.fxml";
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
                    resourceName = "rewards-screen.fxml";
                    break;
            }


            if(telaAtual != null)
                telas.add(resourceName);

            telaAtual = resourceName;
            cb.navigateCb(telaAtual);

        }
        catch(Exception e)
        {

        }

    }
}
