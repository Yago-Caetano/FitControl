package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.models.CatracaModelTest;
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
    }

    private void testSerialCommFunctions(){ new SerialCommunicatorSingletonTest().test();}
}
