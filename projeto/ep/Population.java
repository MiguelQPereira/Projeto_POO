package ep;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import dss.Sim;
import dss.Event;

public class Population implements Pop{

    private final int maxPop;
    private final Sim sim;
    
    private final int u;
    private final int q;
    private final int s;

    private int epiConter;

    EvFactory fabMorte;
    EvFactory fabMut;
    EvFactory fabRep;
    EvFactory fabPrint;

    private ArrayList<Individuo> pop;
    private ListIterator<Individuo> iterator;
    
    public Population(int maxPop, Sim sim, int u, int q, int s) {
        this.maxPop = maxPop;

        this.pop = new ArrayList<Individuo>();
        this.iterator = this.pop.listIterator();

        this.sim = sim;

        this.u = u;
        this.q = q;
        this.s = s;

        this.epiConter = 0;

        this.fabMorte = new MorteFactory();
        this.fabMut = new MutFactory();
        this.fabRep = new RepFactory();
        this.fabPrint = new PrintFactory();

        //Adiciona eventos print
        for (int i = 1; i <= 20; i++) {
            this.sim.addEv(this.fabPrint.createEvent(this, null, (i + 1) / this.sim.getMaxTime()));
        }
    }

    public void addInd(Individuo ind) {

        //Ordenar População

        if (ind.getConfort() == 1)
        {
            this.sim.emptiesPec();

            this.sim.addEv(this.fabPrint.createEvent(this, null, this.sim.getTime()));

            return;
        }

        this.addEvMorte(ind);
        this.addEvMut(ind);
        this.addEvRep(ind);
        
        if (this.pop.size() > maxPop) {
            epidmia();
        }

        return;
    }

    public void remInd(Individuo ind) {

        this.pop.remove(ind);

        return;
    }

    private void epidmia() {

        if (pop.size() < 6) return;

        Random random = new Random();
        double randomVariable;
        Individuo ele;

        this.iterator = this.pop.listIterator(5);

        while (iterator.hasNext()) {
            ele = iterator.next();

            randomVariable = random.nextDouble();

            if (randomVariable > (ele.getConfort() * 2 / 3)) this.remInd(ele);
        }

        this.epiConter++;

        //Rem eventos

        return;
    }

    public ArrayList<Individuo> getPop() {

        return this.pop;
    }

    public int getMu() {

        return this.u;
    }

    public int getRho() {

        return this.q;
    }

    public int getSigma() {

        return this.s;
    }

    public int getEpiConter() {

        return this.epiConter;
    }

    public double getSimTime() {
        return this.sim.getTime();
    }

    public int getEvsNum() {
        return this.sim.getEvsNum();
    }

    public void addEvMorte(Individuo ind) {

        double inst = this.sim.getTime();

        Event Ev = this.fabMorte.createEvent(this, ind, inst);

        if (ind.getDeadTime() == 0) this.sim.addEv(Ev);

        return;
    }

    public void addEvMut(Individuo ind) {

        double inst = this.sim.getTime();

        Event Ev = this.fabMut.createEvent(this, ind, inst);

        if (ind.getDeadTime() > Ev.getTime()) this.sim.addEv(Ev);

        return;
    }

    public void addEvRep(Individuo ind) {

        double inst = this.sim.getTime();

        Event Ev = this.fabRep.createEvent(this, ind, inst);

        if (ind.getDeadTime() > Ev.getTime()) this.sim.addEv(Ev);

        return;
    }

}
