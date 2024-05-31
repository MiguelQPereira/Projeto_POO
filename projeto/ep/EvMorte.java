package ep;

import java.util.concurrent.ThreadLocalRandom;

import dss.Event;

public class EvMorte implements Event {
    
    Population p;
    Individuo ref;

    double time;

    public EvMorte(Population p, Individuo ref, double inst) {

        this.p = p;
        this.ref = ref;

        double mean;
        double exp;

        //calcula média da expressão fornecida
        mean = (1-Math.log(1-this.ref.getConfort()))*this.p.getMu();
                
        //gera número aleatório uniformemente distribuido entre 0 e 1  
        exp = ThreadLocalRandom.current().nextDouble();

        //aplica distribuição exponencial com a média
        this.time = inst - Math.log(1-exp) * mean;

        this.ref.setDeadTime(this.time);
    }

    public void simulate() {

        this.ref.dead();

        this.p.remInd(ref);

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
