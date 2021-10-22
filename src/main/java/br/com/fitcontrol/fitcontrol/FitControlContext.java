package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;
import br.com.fitcontrol.fitcontrol.models.ClienteModel;
import br.com.fitcontrol.fitcontrol.models.RecompensaModel;

public class FitControlContext {

   PadraoDAO daoData;
   ClienteModel cliente;    //isso ta certo Yago?
   RecompensaModel recompensa;     //isso ta certo Yago?

    public PadraoDAO getDaoData() {
        return daoData;
    }

    public void setDaoData(PadraoDAO daoData) {
        this.daoData = daoData;
    }

    public ClienteModel getClienteData() {
        return cliente;
    }

    public void setClienteData(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public RecompensaModel getRecompensaData() {
        return recompensa;
    }

    public void setRecompensaData(RecompensaModel recompensa) {
        this.recompensa = recompensa;
    }
}
