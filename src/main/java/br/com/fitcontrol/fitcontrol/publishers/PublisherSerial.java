package br.com.fitcontrol.fitcontrol.publishers;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.listenners.AccessCheckInListenner;
import br.com.fitcontrol.fitcontrol.listenners.AcessCheckOutListenner;

public class PublisherSerial {

    EventManager mEventManager;

    public PublisherSerial(EventManager eventManager){
        mEventManager = eventManager;
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_IN,new AccessCheckInListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_OUT,new AcessCheckOutListenner());
    }

    public void CheckInEvent()
    {
        FitControlContext c = new FitControlContext();
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_IN,c);
    }

    public void CheckOutEvent()
    {
        FitControlContext c = new FitControlContext();
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_OUT,c);
    }
}
