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

        this.p.incrObs();

        System.out.println("Obervation " + this.p.getObsNum());
        System.out.println("\t\t\tPresent Instant: " + this.p.getSimTime());
        System.out.println("\t\t\tNumber of realized events: " + this.p.getEvsNum());
        System.out.println("\t\t\tPopulation size: " + this.p.getPopSize());
        System.out.println("\t\t\tNumber of epidemics: " + this.p.getEpiConter());
        System.out.println("\t\t\tBest distribution of the patrols: " + this.p.getPop().get(0).getDistribution());
        System.out.println("\t\t\tEmpire policing time: " + this.p.getPop().get(0).getTime());
        System.out.println("\t\t\tConfort: " + this.p.getPop().get(0).getConfort());
        System.out.println("\t\t\tOther candidate distributions: ");
        System.out.println("\t\t\t\t\t\t" + this.p.getPop().get(1).toString());
        System.out.println("\t\t\t\t\t\t" + this.p.getPop().get(2).toString());
        System.out.println("\t\t\t\t\t\t" + this.p.getPop().get(3).toString());
        System.out.println("\t\t\t\t\t\t" + this.p.getPop().get(4).toString());
        System.out.println("\t\t\t\t\t\t" + this.p.getPop().get(5).toString());

        return;
    }

    public boolean isAlive() {

        return true;
    }

    public double getTime() {

        return this.time;
    }
}
