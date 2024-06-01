package ep;

import dss.Event;

/**
 * MorteFactory class extends EvFactory and provides implementation 
 * for creating death events within a Population.
 */
class MorteFactory extends EvFactory{

    /**
     * Creates a new death event.
     * 
     * @param p Population.
     * @param ref The reference Individuo for which the death event is created.
     * @param time The time instant of the death event.
     * @return A new death Event object.
     */
    @Override
    public Event newEvent(Population p, Individuo ref, double time) {
        return new EvMorte(p, ref, time);
    }
}
