package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.models.*;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingletonTest;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void main()
    {
        testModels();
        testSerialCommFunctions();
        testControllers();
    }

    private void testModels()
    {
        new CatracaModelTest().test();
        new ClienteModelTest().test();
        new EventModelTest().test();
        new PagamentosModelTest().test();
        new RecompensaModelTest().test();

    }

    private void testControllers()
    {
        new ClienteModelTest().test();
    }

    private void testSerialCommFunctions(){ new SerialCommunicatorSingletonTest().test();}
}
