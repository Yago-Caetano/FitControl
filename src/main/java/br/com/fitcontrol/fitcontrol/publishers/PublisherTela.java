package br.com.fitcontrol.fitcontrol.publishers;

import br.com.fitcontrol.fitcontrol.Basis.Entidade;
import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.events.EventManager;
import br.com.fitcontrol.fitcontrol.listenners.*;

import java.sql.SQLException;

public class PublisherTela {

    private static PublisherTela instance = new PublisherTela();

    public static PublisherTela getInstance()
    {
        return instance;
    }

    EventManager mEventManager;

    public PublisherTela()
    {
        if(mEventManager == null)
            return;
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_REGISTER,new UserRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_DELETE,new UserDeleteListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_USER_UPDATE,new UserUpdateListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_REGISTER,new RewardRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_REWARD_UPDATE,new RewardUpdateListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_REGISTER, new CatracaRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_DELETE, new CatracaDeleteListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_UPDATE, new CatracaUpdateListenner());

        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_EMPLOYEE_REGISTER,new EmployeeRegisterListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_EMPLOYEE_UPDATE,new EmployeeUpdateListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_EMPLOYEE_DELETE,new EmployeeDeleteListenner());

        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_GRANT_ACESS, new CatracaGrantAcessListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_BLOCK, new CatracaBlockListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_RESTART, new CatracaRestartListenner());
        mEventManager.subscriber(EnumEventTypes.EVENT_TYPE_CATRACA_UNLOCK, new CatracaUnlockListenner());


    }


    public void registerEventManager(EventManager eventManager){
        mEventManager = eventManager;

    }


    public void RegisterUser(Entidade e)  throws Exception
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_REGISTER,c);
    }
    public void DeleteUser(Entidade e)  throws Exception
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_DELETE,c);
    }
    public void UpdateUser(Entidade e)  throws Exception
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_USER_UPDATE,c);
    }
    public void RegisterReward(Entidade e)  throws Exception
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_REGISTER,c);
    }
    public void UpdateReward(Entidade e)  throws Exception
    {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_REWARD_UPDATE,c);
    }
    public void RegisterCatraca(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_REGISTER,c);
    }
    public void DeleteCatraca(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_DELETE,c);
    }
    public void UpdateCatraca(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_UPDATE,c);
    }

    public void RegisterEmployee(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_EMPLOYEE_REGISTER,c);
    }
    public void UpdateEmployee(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_EMPLOYEE_UPDATE,c);
    }
    public void DeleteEmployee(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_EMPLOYEE_DELETE,c);
    }

    public void grantAcess(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_GRANT_ACESS,c);
    }

    public void blockCatraca(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_BLOCK,c);
    }

    public void restartCatraca(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_RESTART,c);
    }

    public void unlockCatraca(Entidade e) throws Exception {
        FitControlContext c = new FitControlContext();
        c.setEntityData(e);
        mEventManager.notify(EnumEventTypes.EVENT_TYPE_CATRACA_UNLOCK,c);
    }

}
