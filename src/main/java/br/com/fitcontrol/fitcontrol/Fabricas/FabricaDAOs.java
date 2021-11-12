package br.com.fitcontrol.fitcontrol.Fabricas;

import br.com.fitcontrol.fitcontrol.Enums.EnumEntidadesDisponiveis;
import br.com.fitcontrol.fitcontrol.dao.Acesso.AcessoMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Catraca.CatracaMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Cliente.ClienteMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Funcionario.FuncionarioMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Itens_Recompensa.ItensRecompensaMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;
import br.com.fitcontrol.fitcontrol.dao.Pagamento.PagamentosMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Pontuacao.PontuacaoMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.Recompensa.RecompensaMySQLDAO;
import br.com.fitcontrol.fitcontrol.dao.RecompensaHist.RecompensaHistMySQLDAO;

public class FabricaDAOs {
    public static br.com.fitcontrol.fitcontrol.dao.PadraoDAO  Fabrica(EnumEntidadesDisponiveis enumEntidade, main.java.br.com.fitcontrol.fitcontrol.Enums.EnumTipoRepositorio repositorio) {
        switch (repositorio)
        {
            case MYSQL:
               return montaDAOMySQL(enumEntidade);
            default:
                return null;
        }

    }

    private static PadraoDAO montaDAOMySQL(EnumEntidadesDisponiveis enumEntidade) {
        PadraoDAO retorno;
        switch (enumEntidade)
        {

            case FUNCIONARIO :
                retorno = new FuncionarioMySQLDAO();
                break;
            case CLIENTE :
                retorno = new ClienteMySQLDAO();
                break;
            case CATRACA:
                retorno = new CatracaMySQLDAO();
                break;
            case ACESSO:
                retorno = new AcessoMySQLDAO();
                break;
            case PONTUACAO:
                retorno = new PontuacaoMySQLDAO();
                break;
            case PAGAMENTO:
                retorno = new PagamentosMySQLDAO();
                break;
            case ITENS_RECOMPENSA:
                retorno = new ItensRecompensaMySQLDAO();
                break;
            case HISTORIC_RECOMPENSA:
                retorno = new RecompensaHistMySQLDAO();
                break;
            case RECOMPENSA:
                retorno = new RecompensaMySQLDAO();
                break;
            default:
                retorno = null;
                break;
        }
        return retorno;
    }
}
