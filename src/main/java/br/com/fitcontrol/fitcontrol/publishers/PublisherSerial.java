package br.com.fitcontrol.fitcontrol.publishers;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.listenners.AccessCheckInListenner;
import br.com.fitcontrol.fitcontrol.listenners.AcessCheckOutListenner;
import br.com.fitcontrol.fitcontrol.listenners.CatracaErroListenner;
import br.com.fitcontrol.fitcontrol.models.AcessoModel;

import java.sql.SQLException;

public class PublisherSerial {

    EventManager mEventManager;

    public PublisherSerial(EventManager eventManager){
        mEventManager = eventManager;
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_IN,new AccessCheckInListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_OUT,new AcessCheckOutListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_ERRO, new CatracaErroListenner());
    }

    public void CheckInEvent(Entidade acesso) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(acesso);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_IN,c);
    }

    public void CheckOutEvent(Entidade acesso) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(acesso);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_OUT,c);
    }

    public void ErroEvent(Entidade acesso) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(acesso);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_ERRO,c);
    }
}
