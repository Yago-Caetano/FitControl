package br.com.fitcontrol.fitcontrol.models;

import br.com.fitcontrol.fitcontrol.events.EnumEventTypes;
import br.com.fitcontrol.fitcontrol.listenners.ISubscriber;

public class EventModel {

    ISubscriber EventListenner;

    EnumEventTypes EventType;

    public EventModel(ISubscriber eventListenner, EnumEventTypes eventType) {
        EventListenner = eventListenner;
        EventType = eventType;
    }

    public ISubscriber getEventListenner() {
        return EventListenner;
    }

    public EnumEventTypes getEventType() {
        return EventType;
    }
}
