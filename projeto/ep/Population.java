package ep;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import dss.Sim;
import ep.Individuo;
import dss.Event;

public class Population implements Pop{

    private final int maxPop;
    private final Sim sim;
    
    private final int u;
    private final int q;
    private final int s;

    private int epiConter;
    private int obs;

    private EvFactory fabMorte;
    private EvFactory fabMut;
    private EvFactory fabRep;
    private EvFactory fabPrint;

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
        this.obs = 0;

        this.fabMorte = new MorteFactory();
        this.fabMut = new MutFactory();
        this.fabRep = new RepFactory();
        this.fabPrint = new PrintFactory();

        //Adiciona eventos print
        for (int i = 1; i <= 20; i++) {
            this.sim.addEv(this.fabPrint.createEvent(this, null, ((double) this.sim.getMaxTime() * i) / 20));
            //System.out.println(this.sim.getMaxTime() * i / 20);
        }
    }

    public void addInd(Individuo ind) {

        //Ordenar População

        this.iterator = this.pop.listIterator();

        int i = 0;
        while (this.iterator.hasNext()) {
            Individuo ele = this.iterator.next();

            if (ele.getConfort() < ind.getConfort()) break;
            i++;
        }

        pop.add(i, ind);

        if (ind.getConfort() == 1)
        {
            perfeito();
            return;
        }

        this.addEvMorte(ind);
        this.addEvMut(ind);
        this.addEvRep(ind);
        
        if (this.pop.size() > this.maxPop) {
            //System.out.println("Epidemia " + this.maxPop + " : " + this.pop.size() + " maxtime: " + this.sim.getMaxTime());
            epidmia();
        }

        return;
    }

    public void perfeito() {

        this.sim.emptiesPec();

        this.sim.addEv(this.fabPrint.createEvent(this, null, this.sim.getTime()));

        return;
    }

    public void updatePos(Individuo ind) {

        this.pop.remove(ind);

        this.iterator = this.pop.listIterator();

        int i = 0;
        while (this.iterator.hasNext()) {
            Individuo ele = this.iterator.next();

            if (ele.getConfort() < ind.getConfort()) break;
            i++;
        }

        pop.add(i, ind);

        return;
    }

    public ArrayList<Individuo> getTop5() {

        ArrayList<Individuo> top5 = new ArrayList<Individuo>();

        int i = 0;

        for (Individuo ind : this.pop) {

            if (i == 5) break;

            if (top5.isEmpty()) {
                top5.add(ind);
                i++;
                continue;
            }

            if (ind.getConfort() != top5.get(top5.size() - 1).getConfort()) {
                top5.add(ind);
                i++;
            }
        }

        return top5;
    }

    public ArrayList<Individuo> getTop6() {

        ArrayList<Individuo> top6 = new ArrayList<Individuo>();

        int i = 0;

        for (Individuo ind : this.pop) {

            if (i == 6) break;

            if (top6.isEmpty()) {
                top6.add(ind);
                i++;
                continue;
            }

            if (ind.getConfort() != top6.get(top6.size() - 1).getConfort()) {
                top6.add(ind);
                i++;
            }
        }

        return top6;
    }

    public void remInd(Individuo ind) {

        this.pop.remove(ind);

        return;
    }

    private void epidmia() {

        if (pop.size() < 6) return;

        Random random = new Random();
        double randomVariable;

        ArrayList<Individuo> remove = new ArrayList<Individuo>();
        ArrayList<Individuo> top5 = this.getTop5();

        for (Individuo i : this.pop) {

            if (top5.contains(i)) continue;

            randomVariable = random.nextDouble();
            if (randomVariable > (i.getConfort() * 2 / 3)) remove.add(i);
        }

        for (Individuo i : remove) {
            this.pop.remove(i);
        }

        this.epiConter++;

        this.sim.cleanPec();

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

    public int getPopSize() {
        return this.pop.size();
    }

    public int getObsNum() {
        return this.obs;
    }

    public void incrObs() {
        this.obs++;
    }

}
