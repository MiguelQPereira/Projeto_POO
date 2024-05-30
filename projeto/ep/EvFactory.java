package ep;

import dss.Event;

abstract class EvFactory {
    
    public abstract Event newEvent();

    public Event createEvent() {

        Event evento = newEvent();

        return evento;
    }
}
