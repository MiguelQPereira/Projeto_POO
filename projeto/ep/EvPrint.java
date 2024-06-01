package ep;

import java.util.ArrayList;

import dss.Event;

/**
 * EvPrint class implements the Event interface to print observations 
 * about a Population at a given time instant during simulation.
 */
public class EvPrint implements Event {

    Population p;
    double time;

    /**
     * Constructs an EvPrint object.
     * 
     * @param p Population.
     * @param ref Individuo.
     * @param inst The time instant of the observation.
     */
    public EvPrint(Population p, Individuo ref, double inst) {

        this.p = p;
        this.time = inst;
    }

    /**
     * Simulates an observation of the Population.
     */
    public void simulate() {

        this.p.incrObs();

        System.out.println("Obervation " + this.p.getObsNum());
        System.out.printf("\t\tPresent Instant: %.2f\n", this.p.getSimTime());
        System.out.println("\t\tNumber of realized events: " + this.p.getEvsNum());
        System.out.println("\t\tPopulation size: " + this.p.getPopSize());
        System.out.println("\t\tNumber of epidemics: " + this.p.getEpiConter());

        ArrayList<Individuo> top6 = this.p.getTop6();
        
        if (top6.isEmpty()) return;

        System.out.println("\t\tBest distribution of the patrols: " + top6.get(0).getDistribution());
        System.out.println("\t\tEmpire policing time: " + top6.get(0).getTime());
        System.out.printf("\t\tConfort: %.3f\n", top6.get(0).getConfort());
        System.out.println("\t\tOther candidate distributions: ");

        for (int i = 1; i < 6 && i < top6.size(); i++) {
            System.out.println("\t\t\t\t\t\t" + top6.get(i).toString());
        }

        return;
    }

    /**
     * Checks if the event is still active.
     * 
     * @return Always returns true, as the event is always considered active.
     */
    public boolean isAlive() {

        return true;
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
