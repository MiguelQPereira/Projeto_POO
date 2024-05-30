package ep;

import dss.Event;

class MorteFactory extends EvFactory{

    @Override
    public Event newEvent() {
        return new EvMorte();
    }
}
