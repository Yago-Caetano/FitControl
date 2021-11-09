package br.com.fitcontrol.fitcontrol.models;


import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatracaModelTest {

    /**
     *  This is a dummy example of how use junit API
     */
    @Test
    public void test()
    {
        CatracaModel catracaModel = new CatracaModel("1","Catraca1");

        //chack if the name is Catraca1
        assertEquals(catracaModel.getModelo(),"Catraca1");

        //check if the id is 1
        assertEquals(catracaModel.getId(),1);
    }
}
