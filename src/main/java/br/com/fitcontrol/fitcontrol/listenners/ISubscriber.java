package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;

import java.sql.SQLException;

public interface ISubscriber {
    void update(FitControlContext context) throws Exception;
}
