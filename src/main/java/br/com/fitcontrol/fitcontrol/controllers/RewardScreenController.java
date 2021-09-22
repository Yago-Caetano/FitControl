package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.models.RecompensaModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RewardScreenController implements Initializable {

    @FXML
    private GridPane gridRecompensas;

    private List<RecompensaModel> Recompensas = new ArrayList<RecompensaModel>();

    private void completaLista()
    {
        Recompensas.add(new RecompensaModel(1,15000.0,"Coqueteleira"));
        Recompensas.add(new RecompensaModel(2,7000.0,"Abraçadeira"));
        Recompensas.add(new RecompensaModel(3,150000.0,"Mensalidade Gratuita"));
        Recompensas.add(new RecompensaModel(4,75000.0,"Camiseta"));
        Recompensas.add(new RecompensaModel(5,130000.0,"Garrafa térmica"));
        Recompensas.add(new RecompensaModel(6,300000.0,"Mochila"));
    }

    private void carregaGrid()
    {
        completaLista();
        int linha = 0;
        int col = -1;

        for(RecompensaModel rec : Recompensas)
        {

            Label Titulo = new Label(rec.getDescricao());

            File file = new File("src/main/resources/br/com/fitcontrol/fitcontrol/icons/award_icon.png");
            Image image = new Image(file.toURI().toString());

            ImageView Icone = new ImageView();
            Icone.setImage(image);

            Icone.setFitWidth(200);
            Icone.setFitHeight(150);

            //Titulo.setAlignment(Pos.CENTER);

            Button BtAplicar = new Button();
            BtAplicar.setText("Aplicar");
            //BtAplicar.setAlignment(Pos.CENTER);


            VBox vboxAux = new VBox();
            vboxAux.getStyleClass().add("reward-item");
            vboxAux.setPadding(new Insets(32,32,32,32));

            vboxAux.getChildren().addAll(Titulo,Icone,BtAplicar);

            HBox hboxAux = new HBox();
            hboxAux.setPadding(new Insets(10, 50, 50, 50));
            hboxAux.setSpacing(10);

            hboxAux.getChildren().add(vboxAux);

            HBox.setMargin(vboxAux,new Insets(16,16,16,16));
            //hboxAux.setPadding(new Insets(32,32,32,32));

            if(col < 2)
                col++;
            else{
                col = 0;
                linha++;
            }

            gridRecompensas.add(hboxAux,col,linha);


        }

        gridRecompensas.setVgap(32);


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregaGrid();

    }
}
