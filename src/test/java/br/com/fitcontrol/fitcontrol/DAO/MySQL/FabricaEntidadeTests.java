package br.com.fitcontrol.fitcontrol.DAO.MySQL;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.Enums.EnumTipoUsuarios;
import br.com.fitcontrol.fitcontrol.models.AcessoModel;
import br.com.fitcontrol.fitcontrol.models.CatracaModel;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.FuncionarioModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FabricaEntidadeTests {

    public static Entidade FabricarEntidade(EnumEntidadesDisponiveis ent)
    {
        Entidade retorno;
        switch (ent)
        {
            case ACESSO:
            retorno =  CreateAcessoTest();
                break;
            case CLIENTE:
                retorno =  CreateCliente();
                break;
            case FUNCIONARIO:
                retorno =  CreateFuncionario();
                break;
            case CATRACA:
                retorno =  CreateCatraca();
                break;
            //case FUNCIONARIO:
            //retorno = new FuncionarioModel();
            // break;
            default:
                retorno = new Entidade();
                break;
        }
        return retorno;
    }
    private static AcessoModel CreateAcessoTest()
    {
        java.sql.Date data=null;
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data = new java.sql.Date(fmt.parse("08/11/2021").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        AcessoModel acesso= new AcessoModel();
        acesso.setId();
        acesso.setData(data);
        return acesso;
    }
    private static ClienteModel CreateCliente()
    {
        ClienteModel cliente= new ClienteModel();
        cliente.setId();
        cliente.setLogin("l111");
        //cliente.setNivel(EnumTipoUsuarios.CLIENTE.getCode());
        cliente.setNome("cliente de teste");
        cliente.setPontos(0);
        cliente.setTelefone("tell");
        return cliente;
    }
    private static FuncionarioModel CreateFuncionario()
    {
        FuncionarioModel func= new FuncionarioModel();
        func.setId();
        func.setLogin("ff11");
        func.setNivel(EnumTipoUsuarios.FUNCIONARIO.getCode());
        func.setNome("funcionario de teste");
        //func.setPontos(0);
        func.setTelefone("tell");
        return func;
    }
    private static CatracaModel CreateCatraca()
    {
        CatracaModel catr= new CatracaModel();
        catr.setId();
        catr.setModelo("Modelo de catraca1");
        catr.setNome("Nome de catraca1");
        return catr;
    }
}
