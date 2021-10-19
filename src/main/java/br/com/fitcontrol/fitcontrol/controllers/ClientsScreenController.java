package br.com.fitcontrol.fitcontrol.controllers;


import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientsScreenController implements Initializable {

    @FXML
    public TableView<ClienteModel> tabela;
    @FXML
    public TableColumn<ClienteModel, Integer> id;
    @FXML
    public  TableColumn<ClienteModel, String> nome;
    @FXML
    public TableColumn<ClienteModel, String> email;
    @FXML
    public TableColumn<ClienteModel, String> telefone;
    @FXML
    public TableColumn<ClienteModel, Integer> ponto;
    @FXML
    public TableColumn<ClienteModel,Void> acao;



    private NavigationSingleton navigation;
    @FXML
    private Button voltar,novoCliente;

    @FXML
    protected void voltarClicked() {
        executeNavigation(NavigationSingleton.MAIN_SCREEN);
    }
    @FXML
    protected void novoClienteClicked() {
        executeNavigation(NavigationSingleton.CLIENTS_EDIT_SCREEN);
    }

    ObservableList<ClienteModel>  list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        navigation = NavigationSingleton.getInstance();

        id.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, Integer>("id"));
        nome.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("nome"));
        email.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("email"));
        telefone.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, String>("telefone"));
        ponto.setCellValueFactory(
                new PropertyValueFactory<ClienteModel, Integer>("pontos"));

        carregarDados();
        colunaAcoes();

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

    private void carregarDados(){

        list.addAll(
                new ClienteModel(1, "Douglas", "Douglas@gmail.com", "4992-8922", 20),
                new ClienteModel(2, "Rafael", "Rafael@gmail.com", "3471-1195", 30),
                new ClienteModel(3, "Julio", "Julio@gmail.com", "2781-9895", 70)
        );

        tabela.setItems(list);
    }

    private void colunaAcoes(){

        Callback<TableColumn<ClienteModel, Void>, TableCell<ClienteModel, Void>> cellFactory = new Callback<TableColumn<ClienteModel, Void>, TableCell<ClienteModel, Void>>() {
            @Override
            public TableCell<ClienteModel, Void> call(final TableColumn<ClienteModel, Void> param) {
                final TableCell<ClienteModel, Void> cell = new TableCell<ClienteModel, Void>() {

                    private final Button btnDeletar = new Button("Deletar");

                    {
                        btnDeletar.setStyle("-fx-background-color:#e05f55;");
                        btnDeletar.setOnAction((ActionEvent event) -> {
                            ClienteModel data = getTableView().getItems().get(getIndex());
                        });

                    }

                    private final Button btnEditar = new Button("Editar");

                    {
                        btnEditar.setStyle("-fx-background-color:#2bc5d6;");

                        btnEditar.setOnAction((ActionEvent event) -> {
                            ClienteModel aluno = getTableView().getItems().get(getIndex());

                            FXMLLoader loader = new FXMLLoader(FitControlMain.class.getResource("client-edit-screen.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ClientsScreenController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            ClientEditScreenController clientEditController = loader.getController();

                            clientEditController.setUpdate(true);
                            clientEditController.preencheTextField(aluno);
                            Parent parent = loader.getRoot();
                            navigation.getStage().setScene(new Scene(parent));

                        });

                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox pane = new HBox(btnDeletar, btnEditar);
                            pane.setStyle("-fx-alignment:center");
                            setGraphic(pane);
                        }
                    }
                };
                return cell;
            }
        };

        acao.setCellFactory(cellFactory);

        tabela.getColumns().add(acao);
    }
}
