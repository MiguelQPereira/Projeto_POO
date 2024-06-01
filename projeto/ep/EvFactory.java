package ep;

import dss.Event;

/**
 * Abstract factory class for creating Event instances.
 * This class defines a method for creating events and an abstract method to be implemented by subclasses for creating specific types of events.
 */
abstract class EvFactory {
    
    /**
     * Creates a new event with the given parameters.
     * This method is abstract and is implemented by subclasses to create specific types of events.
     *
     * @param p Population.
     * @param ref The individual for the event.
     * @param time The time at which the event occurs.
     * @return A new Event instance.
     */

    public abstract Event newEvent(Population p, Individuo ref, double time);

    /**
     * Factory method to create an event.
     * This method uses the {@link #newEvent(Population, Individuo, double)} method to create a new event instance.
     *
     * @param p Population.
     * @param ref The individual for the event.
     * @param time The time at which the event occurs.
     * @return A new Event instance.
     */
    public Event createEvent(Population p, Individuo ref, double time) {

        Event evento = newEvent(p, ref, time);

        return evento;
    }
}
