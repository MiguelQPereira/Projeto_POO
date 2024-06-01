package dss;
/**
 * The Simulation class manages the execution of the simulation process.
 * It scheduals and executes the events.
 */
public class Simulation implements Sim{
    /** Maximum simulation time. */
    private final int t;

    /** Number of events processed. */
    private int numEvs;

    /** Current simulation time. */
    private double instante;

    /** Event queue. */
    private PecInt Evs;

    /**
     * Constructs a Simulation object with a specified maximum time
     * 
     * @param t Maximum simulation time.
     */
    public Simulation(int t) {

        this.t = t;
        this.instante = 0;
        this.Evs = Pec.getPec();
    }

    /**
     * Runs the simulation by processing events until the event queue is empty.
     */
    public void runSim() {

        while (!Evs.isEmpty()) {

            Event next = Evs.getNext();

            this.instante = next.getTime();
    
            next.simulate();

            this.numEvs++;
        }

        return ;
    }

    /**
     * Adds an event to the simulation if there is time to be processed.
     * 
     * @param ev Event to add.
     */
    public void addEv(Event ev) {

        if (ev.getTime() > this.t || ev.getTime() < 0) return;

        this.Evs.add(ev);

        return;
    }

    /**
     * Gets the current simulation time.
     * 
     * @return Current simulation time.
     */
    public double getTime() {
        return this.instante;
    }

    /**
     * Gets the maximum simulation time.
     * 
     * @return Maximum simulation time.
     */
    public int getMaxTime() {
        return this.t;
    }

    /**
     * Gets the number of events processed.
     * 
     * @return Number of events processed.
     */
    public int getEvsNum() {
        return this.numEvs;
    }

    /**
     * Empties the event queue, removing all scheduled events.
     */
    public void emptiesPec() {

        this.Evs.empties();

        return;
    }

    /**
     * Cleans the event queue, removing events of dead individuals.
     */
    public void cleanPec() {

        this.Evs.clean();

        return;
    }
}
