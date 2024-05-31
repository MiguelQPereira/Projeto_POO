package ep;

import java.util.concurrent.ThreadLocalRandom;

import dss.Event;

public class EvRep implements Event {
    
    Population p;
    Individuo ref;

    double time;

    public EvRep(Population p, Individuo ref, double inst) {

        this.p = p;
        this.ref = ref;

        double mean;
        double exp;
        
        //calcula média da expressão fornecida
        mean = (1-Math.log(this.ref.getConfort()))*this.p.getRho();

        //gera número aleatório uniformemente distribuido entre 0 e 1  
        exp = ThreadLocalRandom.current().nextDouble();

        //aplica distribuição exponencial com a média
        this.time = inst - Math.log(1-exp)*mean;
    }

    public void simulate() {

        this.p.addInd(ref.rep());

        this.p.addEvRep(ref);

        return;
    }

    public boolean isAlive() {

        if (this.p.getPop().contains(this.ref)) return true;

        return false;
    }

    public double getTime() {

        return this.time;
    }
}
