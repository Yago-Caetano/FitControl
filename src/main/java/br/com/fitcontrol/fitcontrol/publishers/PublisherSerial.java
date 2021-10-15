package br.com.fitcontrol.fitcontrol.publishers;

import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.listenners.AccessCheckInListenner;
import br.com.fitcontrol.fitcontrol.listenners.AcessCheckOutListenner;
import br.com.fitcontrol.fitcontrol.listenners.UserRegisterListenner;

public class PublisherSerial {

    EventManager mEventManager;

    public PublisherSerial(EventManager eventManager){
        mEventManager = eventManager;
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_IN,new AccessCheckInListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_OUT,new AcessCheckOutListenner());
    }

    public void CheckInEvent()
    {
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_IN);
    }

    public void CheckOutEvent()
    {
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_ACESS_CHECK_OUT);
    }

}
