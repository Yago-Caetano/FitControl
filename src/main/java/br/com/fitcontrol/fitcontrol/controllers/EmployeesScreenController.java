package br.com.fitcontrol.fitcontrol.controllers;


import br.com.fitcontrol.fitcontrol.FitControlMain;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;

import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.navigation.iNavCallback;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeesScreenController implements Initializable {

    @FXML
    public TableView<FuncionarioModel> tabela;
    @FXML
    public TableColumn<FuncionarioModel, String> id;
    @FXML
    public  TableColumn<FuncionarioModel, String> nome;
    @FXML
    public TableColumn<FuncionarioModel, Integer> nivel;
    @FXML
    public TableColumn<FuncionarioModel,Void> acao;

    private NavigationSingleton navigation;

    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack(new iNavCallback() {
                @Override
                public void navigateCb(String screenName) throws IOException {
                    FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                    Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                    navigation.getStage().setScene(scene);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void NovoFuncionarioClicked() {
        executeNavigation(NavigationSingleton.EMPLOYEE_EDIT_SCREEN);
    }

    ObservableList<FuncionarioModel>  list = FXCollections.observableArrayList();
    FuncionarioMySQLDAO dao = new FuncionarioMySQLDAO();

    private void executeNavigation(int screenId)
    {
        navigation.navigate(screenId, new iNavCallback() {
            @Override
            public void navigateCb(String screenName) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(FitControlMain.class.getResource(screenName));
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                navigation.getStage().setScene(scene);
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){

        navigation = NavigationSingleton.getInstance();

        id.setCellValueFactory(
                new PropertyValueFactory<FuncionarioModel, String>("id"));
        nome.setCellValueFactory(
                new PropertyValueFactory<FuncionarioModel, String>("nome"));
        nivel.setCellValueFactory(
                new PropertyValueFactory<FuncionarioModel, Integer>("nivel"));


        /*ObservableList<FuncionarioModel> list = FXCollections.observableArrayList(new FuncionarioModel("1" ,"Jeferson",1),
                new FuncionarioModel("2" ,"Claudia",2),new FuncionarioModel("3" ,"Roberta",3),new FuncionarioModel("4" ,"Murilo",1));

        tabela.setItems(list);*/

        try {
            carregarDados();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        colunaAcoes();

    }

    public void carregarDados() throws Exception {

        ObservableList<FuncionarioModel> lista = FXCollections.observableArrayList(dao.lista());

        tabela.setItems(lista);
    }
    private void colunaAcoes(){

        Callback<TableColumn<FuncionarioModel, Void>, TableCell<FuncionarioModel, Void>> cellFactory = new Callback<TableColumn<FuncionarioModel, Void>, TableCell<FuncionarioModel, Void>>() {
            @Override
            public TableCell<FuncionarioModel, Void> call(final TableColumn<FuncionarioModel, Void> param) {
                final TableCell<FuncionarioModel, Void> cell = new TableCell<FuncionarioModel, Void>() {

                    private final Button btnDeletar = new Button("Deletar");
                    {
                        btnDeletar.setStyle("-fx-background-color:#e05f55;");
                        btnDeletar.setOnAction((ActionEvent event) -> {
                            FuncionarioModel funcionario = getTableView().getItems().get(getIndex());

                            PublisherTela p =  PublisherTela.getInstance();
                            try {
                                p.DeleteEmployee(funcionario);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                carregarDados();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });

                    }

                    private final Button btnEditar = new Button("Editar");

                    {
                        btnEditar.setStyle("-fx-background-color:#2bc5d6;");

                        btnEditar.setOnAction((ActionEvent event) -> {
                            FuncionarioModel funcionario = getTableView().getItems().get(getIndex());

                            FXMLLoader loader = new FXMLLoader(FitControlMain.class.getResource("employee-edit-screen.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(EmployeesScreenController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            EmployeeEditScreenController employeeEditController = loader.getController();

                            employeeEditController.setUpdate(true);
                            employeeEditController.preencheTextField(funcionario);
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
