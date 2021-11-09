package br.com.fitcontrol.fitcontrol.models;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecompensaModelTest
{
    @Test
    public void test()
    {
        RecompensaModel recompensa = new RecompensaModel("1", 100, "Recompensa1");

        //chack if the description is Recompensa1
        assertEquals(recompensa.getDescricao(),"Recompensa1");

        //chack if the needed points is 100
        assertEquals(recompensa.getPontosNecessarios(),100);

        //check if the id is 1
        assertEquals(recompensa.getId(),1);
    }
}
