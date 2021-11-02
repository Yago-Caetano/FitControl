package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Catraca.CatracaMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Recompensa.RecompensaMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.models.RecompensaModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.junit.jupiter.api.parallel.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RewardScreenController implements Initializable {

    @FXML
    private GridPane gridRecompensas;

    private NavigationSingleton navigation;

    @FXML
    private Button voltar;
    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws Exception {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                    navigation.getStage().setScene(scene);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void novaRecompensaClicked() {
        executeNavigation(NavigationSingleton.REWARDS_EDIT_SCREEN);
    }


    private void executeNavigation(int screenId)
    {
        navigation.navigate(screenId, new iNavCallback() {
            @Override
            public void navigateCb(String screenName) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
                navigation.getStage().setScene(scene);
            }
        });
    }


    private VBox fillCard(String Titule)
    {
        //create children VBOX
        VBox children = new VBox();
        children.setAlignment(Pos.CENTER);
        //Create label
        Label lbTitule = new Label(Titule);
        lbTitule.setAlignment(Pos.CENTER);
        Font mFont = new Font("System Bold",14);
        lbTitule.setFont(mFont);
        lbTitule.setTextFill(Color.WHITE);

        //Create Image
        ImageView ivIcon = new ImageView();
        File file  = new File("src/main/resources/br/com/fitcontrol/fitcontrol/icons/award_icon.png");

        Image image = new Image(file.toURI().toString());
        ivIcon.setImage(image);
        ivIcon.setFitHeight(150.0);
        ivIcon.setFitWidth(150.0);


        //Create Button
        Button btApply = new Button();
        btApply.setText("APLICAR");

        btApply.setTextFill(Color.WHITE);
        btApply.setStyle("-fx-background-color: none; -fx-border-color: #ffffff; -fx-border-radius: 8; -fx-min-width: 240;");

        //add into VBOX
        children.getChildren().add(lbTitule);
        children.getChildren().add(ivIcon);
        children.getChildren().add(btApply);

        children.setPrefHeight(240.0);
        children.setMinHeight(240.0);

        children.setPrefWidth(100.0);
        children.setMinWidth(100.0);
        children.setStyle("-fx-background-color: #A27811; -fx-background-radius: 16;");

        //insert margin on childs
        children.setMargin(lbTitule,new Insets(8.0,0.0,8.0,0.0));
        children.setMargin(btApply,new Insets(8.0,0.0,8.0,0.0));

        return children;

    }


    private void fillGrid()
    {
        try{

            int colunm = 0;
            int row = 0;

            RecompensaMySQLDAO dao = new RecompensaMySQLDAO();

            List<RecompensaModel> recompensas = dao.lista();

            for(RecompensaModel rec : recompensas)
            {
                VBox vAux = fillCard(rec.getDescricao());

                gridRecompensas.setMargin(vAux,new Insets(8.0,16.0,8.0,16.0));


                gridRecompensas.add(vAux,(colunm),row);

                if((colunm+1)%3 == 0)
                {
                    row++;
                    colunm = 0;
                }
                else
                {
                    colunm++;
                }
            }

        }
        catch(Exception e)
        {

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();

        fillGrid();
    }
}
