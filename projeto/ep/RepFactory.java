package ep;

import dss.Event;

/**
 * RepFactory class extends EvFactory and provides implementation for creating reproduction events within a Population.
 */
class RepFactory extends EvFactory{

    /**
     * Creates a new reproduction event.
     * 
     * @param p Population.
     * @param ref The reference Individuo for which the reproduction event is created.
     * @param time The time instant of the reproduction event.
     * @return A new reproduction Event object.
     */
    @Override
    public Event newEvent(Population p, Individuo ref, double time) {
        return new EvRep(p, ref, time);
    }
}