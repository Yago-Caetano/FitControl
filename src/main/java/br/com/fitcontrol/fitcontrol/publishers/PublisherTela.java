package br.com.fitcontrol.fitcontrol.publishers;

import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.listenners.*;

public class PublisherTela {

    EventManager mEventManager;

    public PublisherTela(EventManager eventManager){
        mEventManager = eventManager;
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_REGISTER, new UserRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_UPDATE, new UserUpdateListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_DELETE, new UserDeleteListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_REGISTER, new RewardRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_UPDATE, new RewardUpdateListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_DELETE, new RewardDeleteListenner());
    }

    public void UserRegisterEvent() {mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_REGISTER);}
    public void UserUpdateEvent() {mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_UPDATE);}
    public void UserDeleteEvent() {mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_DELETE);}
    public void RewardRegisterEvent() {mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_REGISTER);}
    public void RewardUpdateEvent() {mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_UPDATE);}
    public void RewardDeleteEvent() {mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_DELETE);}
}
