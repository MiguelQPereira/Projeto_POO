package ep;

import java.util.ArrayList;

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

    public boolean isAlive() {

        return true;
    }

    public double getTime() {

        return this.time;
    }
}
