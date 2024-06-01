package ep;

import dss.Event;

/**
 * MutFactory class extends EvFactory and provides implementation 
 * for creating mutation events within a Population.
 */
class MutFactory extends EvFactory{

    /**
     * Creates a new mutation event.
     * 
     * @param p Population.
     * @param ref The reference Individuo for which the mutation event is created.
     * @param time The time instant of the mutation event.
     * @return A new mutation Event object.
     */
    @Override
    public Event newEvent(Population p, Individuo ref, double time) {
        return new EvMut(p, ref, time);
    }
}
