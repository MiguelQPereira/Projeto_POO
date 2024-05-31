package ep;

import dss.Event;

class RepFactory extends EvFactory{

    @Override
    public Event newEvent(Population p, Individuo ref, double time) {
        return new EvRep(p, ref, time);
    }
}