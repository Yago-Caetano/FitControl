package br.com.fitcontrol.fitcontrol.Controllers;

import br.com.fitcontrol.fitcontrol.controllers.ClientsScreenController;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ClientsScreenControllerTest {

    ClientsScreenController controller = new ClientsScreenController();
    @Test
    public void test() throws Exception {
        controller.carregarDados();
    }
}
