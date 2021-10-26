package br.com.fitcontrol.fitcontrol;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.dao.PadraoDAO;

public class FitControlContext {

    Entidade entityData;

    public Entidade getEntityData() {
        return entityData;
    }

    public void setEntityData(Entidade entityData) {
        this.entityData = entityData;
    }
}
