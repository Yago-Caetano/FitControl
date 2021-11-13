package br.com.fitcontrol.fitcontrol.popup;

import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;

public class SucessPopUpSingleton {

    private static SucessPopUpSingleton instance = new SucessPopUpSingleton();

    Stage PrimaryStage;

    public static SucessPopUpSingleton getInstance()
    {
        return instance;
    }

    public void registerStage(Stage tgtStage){
        PrimaryStage = tgtStage;
    }


    public void showPopUp(String Message)
    {
        Popup popup = new Popup();

        //Stage dialog = new Stage();
        //dialog.initModality(Modality.APPLICATION_MODAL);
        //dialog.initOwner(PrimaryStage);

        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(8,8,8,8));
        dialogVbox.setStyle("-fx-background-color:#038f28;");
        dialogVbox.setAlignment(Pos.CENTER);

        //insert logo
        ImageView IvLogo = new ImageView();
        File file  = new File("src/main/resources/br/com/fitcontrol/fitcontrol/icons/icone.png");

        Image image = new Image(file.toURI().toString());
        IvLogo.setImage(image);
        IvLogo.setFitHeight(80.0);
        IvLogo.setFitWidth(150.0);
        dialogVbox.getChildren().add(IvLogo);


        //insert text
        Text TxtMessage = new Text(Message);
        TxtMessage.setFill(Color.WHITE);
        TxtMessage.setFont(new Font(18));

        dialogVbox.getChildren().add(TxtMessage);


        //insert "OK" Button
        Button BtOk = new Button();
        BtOk.setText("OK");
        BtOk.setStyle("-fx-background-color: none; -fx-border-color: #ffffff; -fx-border-radius: 8; -fx-min-width: 240;");
        BtOk.setTextFill(Color.WHITE);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e)
            {
                if(!popup.isShowing())
                    popup.show(PrimaryStage);
                else
                    popup.hide();
            }
        };

        // when button is pressed
        BtOk.setOnAction(event);

        dialogVbox.getChildren().add(BtOk);


        //Scene dialogScene = new Scene(dialogVbox, 300, 250);
        //dialog.setScene(dialogScene);
        popup.getContent().add(dialogVbox);
        popup.show(PrimaryStage);
        //dialog.show();
    }

}
