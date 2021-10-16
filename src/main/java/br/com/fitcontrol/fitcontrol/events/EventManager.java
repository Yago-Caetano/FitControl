package br.com.fitcontrol.fitcontrol.events;

import br.com.fitcontrol.fitcontrol.FitControlContext;
import br.com.fitcontrol.fitcontrol.listenners.ISubscriber;
import br.com.fitcontrol.fitcontrol.models.EventModel;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<EventModel> Events;

    public EventManager()
    {
        Events = new ArrayList<EventModel>();
    }

    public void subscriber(EnumEventTypes event, ISubscriber listenner)
    {
        Events.add(new EventModel(listenner,event));
    }

    public void unSubscriber(EnumEventTypes event,ISubscriber listenner){
        for(int i = 0; i < Events.size(); i++) {
            if(Events.get(i).getEventType() == event && Events.get(i).getEventListenner().equals(listenner))
            {
                Events.remove(i);
                break;
            }
        }
    }

    public void notify(EnumEventTypes event, FitControlContext context){
        for (EventModel e: Events) {
            if(e.getEventType() == event)
            {
                e.getEventListenner().update(context);
            }

        }
    }
}
