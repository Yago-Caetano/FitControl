package br.com.fitcontrol.fitcontrol.publishers;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.controllers.EmployeeEditScreenController;
import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.listenners.*;
import br.com.fitcontrol.fitcontrol.serialcom.SerialCommunicatorSingleton;

import java.sql.SQLException;

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
    }



    public void registerEventManager(EventManager eventManager){
        mEventManager = eventManager;

    }


    public void RegisterUser(Entidade e) throws SQLException {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_REGISTER,c);
    }
    public void DeleteUser(Entidade e) throws SQLException {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_DELETE,c);
    }
    public void UpdateUser(Entidade e) throws SQLException {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_UPDATE,c);
    }
    public void RegisterReward(Entidade e) throws SQLException {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_REGISTER,c);
    }
    public void UpdateReward(Entidade e) throws SQLException {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_UPDATE,c);
    }


}
