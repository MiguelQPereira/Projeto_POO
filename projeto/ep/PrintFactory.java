package ep;

import dss.Event;

class PrintFactory extends EvFactory{

    @Override
    public Event newEvent() {
        return new EvPrint();
    }
}
