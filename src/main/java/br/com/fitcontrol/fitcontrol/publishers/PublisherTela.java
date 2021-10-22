package br.com.fitcontrol.fitcontrol.publishers;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.listenners.*;

public class PublisherTela {

    EventManager mEventManager;

    public PublisherTela(EventManager eventManager){
        mEventManager = eventManager;
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_REGISTER,
                new UserRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_UPDATE, new UserUpdateListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_DELETE, new UserDeleteListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_REGISTER, new RewardRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_UPDATE, new RewardUpdateListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_DELETE, new RewardDeleteListenner());
    }

    public void UserRegisterEvent()
    {
        FitControlContext c = new FitControlContext();
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_REGISTER,c);
    }

    public void UserUpdateEvent()
    {
        FitControlContext c = new FitControlContext();
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_UPDATE,c);
    }

    public void UserDeleteEvent(FitControlContext c)
    {
        //FitControlContext c = new FitControlContext();
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_DELETE,c);
    }

    public void RewardRegisterEvent()
    {
        FitControlContext c = new FitControlContext();
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_REGISTER,c);
    }

    public void RewardUpdateEvent(FitControlContext c)
    {
        //FitControlContext c = new FitControlContext();
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_UPDATE,c);
    }

    public void EVENT_TYPE_REWARD_DELETE()
    {
        FitControlContext c = new FitControlContext();
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_DELETE,c);
    }

}
