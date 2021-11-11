package br.com.fitcontrol.fitcontrol.error;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;

public class ErrorPopUp {

    Stage PrimaryStage;

    public ErrorPopUp(Stage primaryStage){
        PrimaryStage = primaryStage;
    }


    public void showError(String ErrorMessage)
    {
        Popup popup = new Popup();

        //Stage dialog = new Stage();
       //dialog.initModality(Modality.APPLICATION_MODAL);
        //dialog.initOwner(PrimaryStage);

        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(8,8,8,8));
        dialogVbox.setStyle("-fx-background-color:#ad102a;");
        dialogVbox.setAlignment(Pos.CENTER);

        //insert logo
        ImageView IvLogo = new ImageView();
        File file  = new File("src/main/resources/br/com/fitcontrol/fitcontrol/icons/icone.png");

        Image image = new Image(file.toURI().toString());
        IvLogo.setImage(image);
        IvLogo.setFitHeight(80.0);
        IvLogo.setFitWidth(150.0);
        dialogVbox.getChildren().add(IvLogo);

        //insert error header
        Text TxtHeader = new Text("Ops!");
        TxtHeader.setFill(Color.WHITE);
        TxtHeader.setFont(new Font(24));

        dialogVbox.getChildren().add(TxtHeader);

        //insert text
        Text TxtMessage = new Text(ErrorMessage);
        TxtMessage.setFill(Color.WHITE);
        dialogVbox.getChildren().add(TxtMessage);


        //insert "OK" Button
        Button BtOk = new Button();
        BtOk.setText("OK");
        BtOk.setStyle("-fx-background-color: none; -fx-border-color: #ffffff; -fx-border-radius: 8; -fx-min-width: 240;");
        BtOk.setTextFill(Color.WHITE);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent e)
                    {
                        if (!popup.isShowing())
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
