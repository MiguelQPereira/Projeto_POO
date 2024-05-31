package ep;

import dss.Event;

class MutFactory extends EvFactory{

    @Override
    public Event newEvent(Population p, Individuo ref, double time) {
        return new EvMut(p, ref, time);
    }
}
