package dss;
/**
 * The Sim interface defines the methods required for running a simulation.
 */
public interface Sim {
    
    /**
     * Runs the simulation by processing events until the event queue is empty.
     */
    void runSim();

    /**
     * Adds an event to the simulation if there is time to be processed.
     * 
     * @param ev Event to add.
     */
    void addEv(Event ev);

    /**
     * Gets the current simulation time.
     * 
     * @return Current simulation time.
     */
    double getTime();

    /**
     * Gets the maximum simulation time.
     * 
     * @return Maximum simulation time.
     */
    int getMaxTime();

    /**
     * Gets the number of events processed.
     * 
     * @return Number of events processed.
     */
    int getEvsNum();

    /**
     * Empties the event queue, removing all scheduled events.
     */
    void cleanPec();

    /**
     * Cleans the event queue, removing events of dead individuals.     */
    void emptiesPec();
}
