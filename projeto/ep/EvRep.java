package ep;

import java.util.concurrent.ThreadLocalRandom;

import dss.Event;

/**
 * EvRep class implements the Event interface to represent reproduction events within a Population at a given time instant during simulation.
 */
public class EvRep implements Event {
    
    Population p;
    Individuo ref;

    double time;

     /**
     * Constructs an EvRep object representing a reproduction event.
     * 
     * @param p Population.
     * @param ref The reference Individuo for reproduction.
     * @param inst The time instant of the event.
     */
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

    /**
     * Simulates the reproduction event by adding a new Individuo to the Population.
     */
    public void simulate() {

        Individuo novo = this.ref.rep();

        this.p.addInd(novo);

        if (novo.getConfort() == 1) return;

        this.p.addEvRep(ref);

        return;
    }

    /**
     * Checks if the event is still active.
     * 
     * @return True if the reference Individuo is still in the Population, otherwise false.
     */
    public boolean isAlive() {

        if (this.p.getPop().contains(this.ref)) return true;

        return false;
    }

    /**
     * Gets the time of the event.
     * 
     * @return The time of the event.
     */
    public double getTime() {

        return this.time;
    }
}
