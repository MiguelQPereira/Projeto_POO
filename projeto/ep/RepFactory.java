package ep;

import dss.Event;

class RepFactory extends EvFactory{

    @Override
    public Event newEvent() {
        return new EvRep();
    }
}