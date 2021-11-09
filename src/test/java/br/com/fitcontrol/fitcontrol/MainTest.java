package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.DAO.MySQL.ConexaoMySQLTest;
import br.com.fitcontrol.fitcontrol.dao.ConexaoMySQL;
import br.com.fitcontrol.fitcontrol.models.CatracaModelTest;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingletonTest;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void main()
    {
        //testModels();
        testSerialCommFunctions();
        testDAO();
    }
    private void testDAO()
    {
        new ConexaoMySQLTest().test();
        ConexaoMySQLTest.VerifyDBTest();
    }

    private void testModels()
    {
        new CatracaModelTest().test();

    }

    private void testSerialCommFunctions(){ new SerialCommunicatorSingletonTest().test();}
}
