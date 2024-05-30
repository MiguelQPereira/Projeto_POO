package ep;

import dss.Event;

public class EvMut implements Event {
    
    Population p;
    Individuo ref;

    public EvMut(Individuo ref) {
        this.ref = ref;
    }

    public void simulate() {

        return;
    }

    public double get_confort() {

        return this.ref.getConfort();
    }

    public int getType() {

        return 3;
    }

}
