package ep;

import dss.Event;

class PrintFactory extends EvFactory{

    @Override
    public Event newEvent(Population p, Individuo ref, double time) {
        return new EvPrint(p, ref, time);
    }
}
