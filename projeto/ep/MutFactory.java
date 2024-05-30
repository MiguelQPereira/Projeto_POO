package ep;

import dss.Event;

class MutFactory extends EvFactory{

    @Override
    public Event newEvent() {
        return new EvMut();
    }
}
