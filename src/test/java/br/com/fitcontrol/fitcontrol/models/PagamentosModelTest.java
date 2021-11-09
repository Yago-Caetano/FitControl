package br.com.fitcontrol.fitcontrol.models;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentosModelTest
{
    @Test
    public void test()
    {
        Date datateste = java.sql.Date.valueOf("2022-01-04");
        PagamentoModel pagamento = new PagamentoModel("1", datateste, 34.99);

        //chack if the date is 04-April-2022
        assertEquals(pagamento.getData(),"2022-01-04");

        //chack if the value is 34.99
        assertEquals(pagamento.getValor(),34.99);

        //check if the id is 1
        assertEquals(pagamento.getId(),1);
    }
}
