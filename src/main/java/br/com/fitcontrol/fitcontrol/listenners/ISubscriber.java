package br.com.fitcontrol.fitcontrol.listenners;

import br.com.fitcontrol.fitcontrol.FitControlContext;

public interface ISubscriber {
    void update(FitControlContext context);
}
