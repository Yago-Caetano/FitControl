package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;

public class FitControlContext {

   PadraoDAO daoData;

    public PadraoDAO getDaoData() {
        return daoData;
    }

    public void setDaoData(PadraoDAO daoData) {
        this.daoData = daoData;
    }
}
