package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.models.CatracaModelTest;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void main()
    {
        testModels();
    }

    private void testModels()
    {
        new CatracaModelTest().test();
    }
}
