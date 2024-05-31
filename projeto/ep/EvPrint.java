package ep;

import dss.Event;

public class EvPrint implements Event {

    Population p;

    public EvPrint() {

    }

    public void simulate() {

        return;
    }

    public double get_confort() {

        return -1;
    }

    public int getType() {

        return 0;
    }

    public void setPop(Population p) {
        this.p = p;

        return;
    }

}
