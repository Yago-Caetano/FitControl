package br.com.fitcontrol.fitcontrol.publishers;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.listenners.AccessCheckInListenner;
import br.com.fitcontrol.fitcontrol.listenners.AcessCheckOutListenner;
import br.com.fitcontrol.fitcontrol.listenners.CatracaErroListenner;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;

public class PublisherTela {

    private static PublisherTela instance = new PublisherTela();

    public static PublisherTela getInstance()
    {
        return instance;
    }

    EventManager mEventManager;

    private PublisherTela()
    {
        //registrar listenners
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_REGISTER,new AccessCheckInListenner());
    }



    public void registerEventManager(EventManager eventManager){
        mEventManager = eventManager;

    }

    public void RegisterUser(Entidade e)
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_REGISTER,c);
    }


}
