package ep;

import dss.Event;

public class EvRep implements Event {
    
    Population p;
    Individuo ref;

    public EvRep() {

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

    public void setPop(Population p) {
        this.p = p;

        return;
    }

    public void setInd(Individuo ind) {
        this.ref = ind;

        return;
    }

}
