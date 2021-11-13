package br.com.fitcontrol.fitcontrol.controllers;

import br.com.fitcontrol.fitcontrol.Acesso.FuncionarioLogadoSingleton;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Pagamento.PagamentosMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.ParametroFiltroDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;
import br.com.fitcontrol.fitcontrol.models.PagamentoModel;
import br.com.fitcontrol.fitcontrol.navigation.NavigationSingleton;
import br.com.fitcontrol.fitcontrol.popup.ErrorPopUpSingleton;
import br.com.fitcontrol.fitcontrol.publishers.PublisherTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentEditScreenController extends PadrãoController implements Initializable {


    @FXML
    public TextField txtID;

    @FXML
    public DatePicker txtData;
    @FXML
    public TextField txtValor;

    @FXML
    public TextField txtEmail;

    @FXML
    public TableView<PagamentoModel> tabela;
    @FXML
    public TableColumn<PagamentoModel, Integer> id;
    @FXML
    public  TableColumn<PagamentoModel, String> data;
    @FXML
    public  TableColumn<PagamentoModel, Double> valor;

    private boolean update;
    private NavigationSingleton navigation;
    private PagamentoModel mPagamento = null;

    @FXML
    protected void voltarClicked() {
        try {
            navigation.goBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Salva ou altera um cliente dependendo do valor do boolean update.
     */
    @FXML
    protected void salvarClicked() throws Exception {
        try {

            if(!validarDados())
                return;

            if(!update)
                mPagamento = new PagamentoModel();

            PagamentosMySQLDAO dao = new PagamentosMySQLDAO();

            mPagamento.setValor(Double.parseDouble(txtValor.getText()));

            mPagamento.setData(Date.valueOf(txtData.getValue()));

            //get client by e-mail
            ClienteMySQLDAO clientDAO = new ClienteMySQLDAO();

            ParametroFiltroDAO param = new ParametroFiltroDAO("Email",txtEmail.getText(),"=");
            List<ParametroFiltroDAO> params = new ArrayList<>();

            params.add(param);

            ArrayList<ClienteModel> userFilter = clientDAO.filtro(params);

            if(userFilter.size()>0)
                mPagamento.setIdCliente(userFilter.get(0).getId());
            else
            {
                ErrorPopUpSingleton.getInstance().showError("Usuário não encontrado");
                return;
            }

            mPagamento.setIdFuncionario(FuncionarioLogadoSingleton.getInstance().getEmployeeSigned().getId());

            PublisherTela p = PublisherTela.getInstance();

            //Verifica se é Edit ou Insert
            if (!update) {                        //Insert
                p.RegisterPayment(mPagamento);
            } else {                            // Edit
                p.UpdatePayment(mPagamento);
            }

        }
        catch(Exception e){
            ErrorPopUpSingleton.getInstance().showError("Falha ao cadastrar");
        }
    }


    public Boolean validarDados() {

        if(txtEmail.getText().length() == 0)
        {
            ErrorPopUpSingleton.getInstance().showError("O campo Email está vázio");
            return false;
        }

        if(txtData.getValue() == null)
        {
            ErrorPopUpSingleton.getInstance().showError("Insira uma data!");
            return false;
        }

        if(txtData.getValue().isAfter(new Date(System.currentTimeMillis()).toLocalDate()))
        {
            ErrorPopUpSingleton.getInstance().showError("Pagamentos futuros não são aceitos");
            return false;
        }

        if(txtValor.getText().length() == 0)
        {
            ErrorPopUpSingleton.getInstance().showError("Insira um valor!");
            return false;
        }

        try{
            if(Double.parseDouble(txtValor.getText()) < 0)
            {
                ErrorPopUpSingleton.getInstance().showError("Valores negativos não são permitidos");
                return false;
            }
        }
        catch (Exception e)
        {
            ErrorPopUpSingleton.getInstance().showError("Valor inválido!");
            return false;
        }
        return true;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigation = NavigationSingleton.getInstance();
    }

    void preencheTextField(PagamentoModel pagamento) {
        try{
            //get user e-mail
            ClienteMySQLDAO clientDAO = new ClienteMySQLDAO();
            ClienteModel cliModel = (ClienteModel) clientDAO.localiza(pagamento.getIdCliente());

            txtEmail.setText(cliModel.getLogin());

            txtData.setValue(pagamento.getData().toLocalDate());
            txtValor.setText(Double.toString(pagamento.getValor()));

            txtEmail.setDisable(true);
            txtEmail.setEditable(false);
        }
        catch(Exception e)
        {

        }

    }


    @Override
    protected void PreviousScreenDataReceived() {
        preencheTextField((PagamentoModel) DataFromPreviousScreen);
        mPagamento = (PagamentoModel) DataFromPreviousScreen;
        update = true;
    }
}
