package ep;

import dss.Event;

abstract class EvFactory {
    
    public abstract Event newEvent(Population p, Individuo ref, double time);

    public Event createEvent(Population p, Individuo ref, double time) {

        Event evento = newEvent(p, ref, time);

        return evento;
    }
}
