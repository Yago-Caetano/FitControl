package br.com.fitcontrol.fitcontrol.models;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteModelTest {

    @Test
    public void test()
    {
        ClienteModel cliente = new ClienteModel(1, "Rafaela", "r","98573",500);

        //Confere se o nome é Rafaela
        assertEquals(cliente.getNome(),"Rafeala");

        //Confere se o email/login é r
        assertEquals(cliente.getLogin(),"r");

        //Confere se o id é 1
        assertEquals(cliente.getId(),1);

        //Confere se o telefone é 98573
        assertEquals(cliente.getTelefone(),"98573");

        //Confere se a quantidade de pontos é 500
        assertEquals(cliente.getPontos(),500);
    }
}
