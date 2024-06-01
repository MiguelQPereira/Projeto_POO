package dss;

/**
 * The Event interface defines the methods required for an event in the simulation.
 * It includes methods for simulating the event, retrieving the event time, and checking the event's status.
 */
public interface Event {

    /**
     * Simulates the event. This method is called to execute the event's behavior.
     */
    void simulate();

    /**
     * Gets the scheduled time of the event.
     * 
     * @return Time at which the event is scheduled to occur.
     */
    double getTime();

    /**
     * Checks if the event is still active or valid.
     * 
     * @return True if the event is active, false otherwise.
     */
    boolean isAlive();
}
