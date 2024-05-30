package ep;

import dss.Event;

public class EvMorte implements Event {
    
    Population p;
    Individuo ref;

    public EvMorte(Individuo ref) {
        this.ref = ref;
    }

    public void simulate() {

        return;
    }

    public double get_confort() {

        return this.ref.getConfort();
    }

    public int getType() {

        return 4;
    }

}
