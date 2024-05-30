package ep;

import dss.Event;

public class EvRep implements Event {
    
    Population p;
    Individuo ref;

    public EvRep(Population p, Individuo ref) {
        this.p = p;
        this.ref = ref;
    }

    public void simulate() {

        return;
    }

    public double get_confort() {

        return this.ref.getConfort();
    }

    public int getType() {

        return 2;
    }

}
