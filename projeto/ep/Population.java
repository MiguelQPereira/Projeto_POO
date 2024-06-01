package ep;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import dss.Sim;
import ep.Individuo;
import dss.Event;

/**
 * Population class represents a population of individuals.
 */
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
    
    /**
     * Constructs a Population object.
     * 
     * @param maxPop The maximum population size.
     * @param sim The simulation object.
     * @param u Death rate.
     * @param q Reproduction rate.
     * @param s Mutation rate.
     */
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

    /**
     * Adds an individual to the population.
     * 
     * @param ind The individual to add.
     */
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
            this.perfeito();
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

    /**
     * Schedules a print event.
     */
    public void perfeito() {

        this.sim.emptiesPec();

        this.sim.addEv(this.fabPrint.createEvent(this, null, this.sim.getTime()));

        return;
    }

    /**
     * Updates the position of an individual in the population.
     * 
     * @param ind The individual to update.
     */
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

    /**
     * Retrieves the top 5 individuals in the population based on comfort level.
     * 
     * @return An ArrayList containing the top 5 individuals.
     */
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

    /**
     * Retrieves the top 6 individuals in the population based on comfort level.
     * 
     * @return An ArrayList containing the top 6 individuals.
     */
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

    /**
     * Removes an individual from the population.
     * 
     * @param ind The individual to remove.
     */
    public void remInd(Individuo ind) {

        this.pop.remove(ind);

        return;
    }

    /**
     * Initiates an epidemic.
     */
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

    /**
     * Retrieves the population.
     * 
     * @return An ArrayList containing the population.
     */
    public ArrayList<Individuo> getPop() {

        return this.pop;
    }

    /**
     * Retrieves the value of parameter u.
     * 
     * @return The value of parameter u.
     */
    public int getMu() {

        return this.u;
    }

    /**
     * Retrieves the value of parameter q.
     * 
     * @return The value of parameter q.
     */
    public int getRho() {

        return this.q;
    }

    /**
     * Retrieves the value of parameter s.
     * 
     * @return The value of parameter s.
     */
    public int getSigma() {

        return this.s;
    }

    /**
     * Retrieves the number of epidemics that occurred.
     * 
     * @return The number of epidemics.
     */
    public int getEpiConter() {

        return this.epiConter;
    }

    /**
     * Retrieves the current simulation time.
     * 
     * @return The current simulation time.
     */
    public double getSimTime() {
        return this.sim.getTime();
    }

    /**
     * Retrieves the number of events in the simulation.
     * 
     * @return The number of events in the simulation.
     */
    public int getEvsNum() {
        return this.sim.getEvsNum();
    }

    /**
     * Adds a death event for the given individual to the simulation.
     * 
     * @param ind The individual for which to add the death event.
     */
    public void addEvMorte(Individuo ind) {

        double inst = this.sim.getTime();

        Event Ev = this.fabMorte.createEvent(this, ind, inst);

        if (ind.getDeadTime() == 0) this.sim.addEv(Ev);

        return;
    }

    /**
     * Adds a mutation event for the given individual to the simulation.
     * 
     * @param ind The individual for which to add the mutation event.
     */
    public void addEvMut(Individuo ind) {

        double inst = this.sim.getTime();

        Event Ev = this.fabMut.createEvent(this, ind, inst);

        if (ind.getDeadTime() > Ev.getTime()) this.sim.addEv(Ev);

        return;
    }

    /**
     * Adds a reproduction event for the given individual to the simulation.
     * 
     * @param ind The individual for which to add the reproduction event.
     */
    public void addEvRep(Individuo ind) {

        double inst = this.sim.getTime();

        Event Ev = this.fabRep.createEvent(this, ind, inst);

        if (ind.getDeadTime() > Ev.getTime()) this.sim.addEv(Ev);

        return;
    }

    /**
     * Retrieves the size of the population.
     * 
     * @return The size of the population.
     */
    public int getPopSize() {
        return this.pop.size();
    }

    /**
     * Retrieves the number of observations made.
     * 
     * @return The number of observations.
     */
    public int getObsNum() {
        return this.obs;
    }

    /**
     * Increments the number of observations made.
     */
    public void incrObs() {
        this.obs++;
    }

}
