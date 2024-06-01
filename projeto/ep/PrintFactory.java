package ep;

import dss.Event;

/**
 * PrintFactory class extends EvFactory and provides implementation for creating print events within a Population.
 */
class PrintFactory extends EvFactory{

    /**
     * Creates a new print event.
     * 
     * @param p Population.
     * @param ref The reference Individuo.
     * @param time The time instant of the print event.
     * @return A new print Event object.
     */
    @Override
    public Event newEvent(Population p, Individuo ref, double time) {
        return new EvPrint(p, ref, time);
    }
}
