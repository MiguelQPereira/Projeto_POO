package ep;

import java.util.concurrent.ThreadLocalRandom;

import dss.Event;

/**
 * Represents a mutation event in the population.
 * This event simulates the mutation of an individual in the population at a specific time.
 */
public class EvMut implements Event {
    
    Population p;
    Individuo ref;
    double time;

    /**
     * Constructs a new EvMut event.
     * Initializes the event with the given population, individual, and initial time.
     * Calculates the time of the event based on an exponential distribution.
     *
     * @param p The population context for the event.
     * @param ref The reference individual for the event.
     * @param inst The initial time of the event.
     */
    public EvMut(Population p, Individuo ref, double inst) {

        this.p = p;
        this.ref = ref;
        
        double mean;
        double exp;
        
        //calcula média da expressão fornecida
        mean = (1-Math.log(this.ref.getConfort()))*this.p.getSigma();

        //gera número aleatório uniformemente distribuido entre 0 e 1  
        exp = ThreadLocalRandom.current().nextDouble();

        //aplica distribuição exponencial com a média
        this.time = inst - Math.log(1-exp)*mean;
    }

    /**
     * Simulates the mutation event.
     * Mutates the individual and updates its position in the population.
     * If the individual's comfort reaches 1, the perfect state is achieved.
     */
    public void simulate() {

        this.ref.mut();

        this.p.updatePos(this.ref);

        if (this.ref.getConfort() == 1) {

            this.p.perfeito();
            return;
        }

        this.p.addEvMut(ref);

        return;
    }

    /**
     * Checks if the individual associated with this event is still alive.
     *
     * @return true if the individual is still part of the population, false otherwise.
     */
    public boolean isAlive() {

        if (this.p.getPop().contains(this.ref)) return true;

        return false;
    }

    /**
     * Gets the time at which this event occurs.
     *
     * @return The time of the event.
     */
    public double getTime() {

        return this.time;
    }

}
