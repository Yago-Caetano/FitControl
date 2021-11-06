package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncionarioModelTest {

    @Test
    public void test()
    {
        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario.setNivel(EnumTipoUsuarios.FUNCIONARIO.getCode());
        funcionario.setSenha("123");
        funcionario.setTelefone("11112");
        funcionario.setLogin("m");
        funcionario.setNome("Murilo");
        funcionario.setId(10);

        //Confere se o nome é Murilo
        assertEquals(funcionario.getNome(),"Murilo");

        //Confere se o email/login é m
        assertEquals(funcionario.getLogin(),"m");

        //Confere se a senha é 123
        assertEquals(funcionario.getSenha(),"123");

        //Confere se o id é 10
        assertEquals(funcionario.getId(),10);

        //Confere se o telefone é 11112
        assertEquals(funcionario.getTelefone(),"11112");

        //Confere se o usuário é um funcionário
        assertEquals(funcionario.getTipoEntidade(),2);
    }
}
