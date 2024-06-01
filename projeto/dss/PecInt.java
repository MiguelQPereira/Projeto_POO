package dss;

/**
 * The PecInt interface defines the methods required for managing a priority event queue in the simulation.
 * It includes methods for retrieving, adding, and managing events within the queue.
 */
public interface PecInt {

    /**
     * Retrieves and removes the next event from the priority queue.
     * 
     * @return Next event.
     */
    Event getNext();

    /**
     * Adds an event to the priority queue.
     * 
     * @param ev Event to add.
     */
    void add(Event ele);

    /**
     * Checks if the priority queue is empty.
     * 
     * @return True if the priority queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Cleans the priority queue by removing events that are no longer active.
     */
    void clean();

    /**
     * Empties the priority queue by removing all events.
     */
    void empties();

}
