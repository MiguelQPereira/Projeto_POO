package ep;

import dss.Event;

class MorteFactory extends EvFactory{

    @Override
    public Event newEvent(Population p, Individuo ref, double time) {
        return new EvMorte(p, ref, time);
    }
}
