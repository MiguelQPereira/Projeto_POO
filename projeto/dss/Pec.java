package dss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The Pec class implements the PecInt interface and manages a priority event queue for the simulation.
 * Ensures that only one instance of the Pec is used throughout the simulation.
 */
public class Pec implements PecInt {
    
    /** Singleton instance of Pec. */
    private static Pec pec = new Pec();

    /** Priority queue to hold the events. */
    private PriorityQueue<Event> q;

    /**
     * Private constructor to initialize the priority queue with a custom comparator.
     */
    private Pec() {

        Comparator<Event> comparator = new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {

                if (e1.getTime() == e2.getTime()) {
                    return 0;
                }
                else if (e1.getTime() > e2.getTime()) {
                    return 1;
                }
                return -1;
            }
        };

        this.q = new PriorityQueue<Event>(comparator);
    }

    /**
     * Gets the instance of Pec.
     * 
     * @return Pec.
     */
    public static Pec getPec() {

        return pec;
    }

    /**
     * Retrieves and removes the next event from the priority queue.
     * 
     * @return Next event.
     */
    public Event getNext() {

        return this.q.poll();
    }

    /**
     * Adds an event to the priority queue.
     * 
     * @param ev Event to add.
     */
    public void add(Event ev) {

        this.q.add(ev);

        return;
    }

    /**
     * Checks if the priority queue is empty.
     * 
     * @return True if the priority queue is empty, false otherwise.
     */
    public boolean isEmpty() {

        return this.q.isEmpty();
    }

    /**
     * Cleans the priority queue by removing events that are no longer active.
     */
    public void clean() {

        ArrayList<Event> remove = new ArrayList<Event>();

        for (Event e : this.q) {
            if (!e.isAlive()) remove.add(e);
        }

        for (Event e : remove) {
            this.q.remove(e);
        }

        return;
    }
    
    /**
     * Empties the priority queue by removing all events.
     */
    public void empties() {

        while (!q.isEmpty()) this.q.poll();

        //System.out.println("Pec apagada: " + this.q.size());

        return;
    }
}
