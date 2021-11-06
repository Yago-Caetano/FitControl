package br.com.fitcontrol.fitcontrol.publishers;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.listenners.*;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;

public class PublisherTela {

    /*private static PublisherTela instance = new PublisherTela();

    public static PublisherTela getInstance()
    {
        return instance;
    }*/

    EventManager mEventManager;

    public PublisherTela(EventManager eventManager)
    {
        mEventManager = eventManager;
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_REGISTER,new UserRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_DELETE,new UserDeleteListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_UPDATE,new UserUpdateListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_REGISTER,new RewardRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_UPDATE,new RewardUpdateListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_REGISTER, new CatracaRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_DELETE, new CatracaDeleteListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_UPDATE, new CatracaUpdateListenner());
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
    public void DeleteUser(Entidade e)
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_DELETE,c);
    }
    public void UpdateUser(Entidade e)
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_UPDATE,c);
    }
    public void RegisterReward(Entidade e)
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_REGISTER,c);
    }
    public void UpdateReward(Entidade e)
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_UPDATE,c);
    }
    public void RegisterCatraca(Entidade e) throws SQLException {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_REGISTER,c);
    }
    public void DeleteCatraca(Entidade e) throws SQLException {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_DELETE,c);
    }
    public void UpdateCatraca(Entidade e) throws SQLException {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_UPDATE,c);
    }


}
