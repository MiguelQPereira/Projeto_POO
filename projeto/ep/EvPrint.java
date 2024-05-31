package ep;

import dss.Event;

public class EvPrint implements Event {

    Population p;
    double time;

    public EvPrint(Population p, Individuo ref, double inst) {

        this.p = p;
        this.time = inst;
    }

    public void simulate() {

        System.out.println("Obervation " + nObs);
        System.out.println("\t\t\tPresent Instant: " + this.p.getSimTime());
        System.out.println("\t\t\tNumber of realized events: " + this.p.getEvsNum());
        System.out.println("\t\t\tPopulation size: ");
        System.out.println("\t\t\tNumber of epidemics: ");
        System.out.println("\t\t\tBest distribution of the patrols: ");
        System.out.println("\t\t\tEmpire policing time: ");
        System.out.println("\t\t\tConfort: ");
        System.out.println("\t\t\tOther candidate distributions: ");
        System.out.println("\t\t\t\t\t\t");
        System.out.println("\t\t\t\t\t\t");
        System.out.println("\t\t\t\t\t\t");
        System.out.println("\t\t\t\t\t\t");

        return;
    }

    public boolean isAlive() {

        return true;
    }

    public double getTime() {

        return this.time;
    }
}
