package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.models.CatracaModelTest;
import br.com.fitcontrol.fitcontrol.models.ClienteModelTest;
import br.com.fitcontrol.fitcontrol.models.EventModelTest;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingletonTest;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void main()
    {
        testModels();
        testSerialCommFunctions();
    }

    private void testModels()
    {
        new CatracaModelTest().test();
        new ClienteModelTest().test();
        new EventModelTest().test();
    }

    private void testSerialCommFunctions(){ new SerialCommunicatorSingletonTest().test();}
}
