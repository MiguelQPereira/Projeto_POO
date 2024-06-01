package ep;

/**
 * The Individuo interface represents an individual entity within a population.
 */
public interface Individuo {
    
    /**
     * Reproduces, creating a new Individuo.
     * 
     * @return A new Individuo resulting from reproduction.
     */
    Individuo rep();

    /**
     * Performs mutation on the Individuo.
     */
    void mut();

    /**
     * Marks the Individuo as dead.
     */
    void dead();

    /**
     * Gets the comfort level of the Individuo.
     * 
     * @return The comfort level of the Individuo.
     */
    double getConfort();

    /**
     * Sets the time at which the Individuo dies.
     * 
     * @param t The time at which the Individuo dies.
     */
    void setDeadTime(double t);

    /**
     * Gets the time at which the Individuo died.
     * 
     * @return The time at which the Individuo died.
     */
    double getDeadTime();

    /**
     * Gets the time of the Individuo.
     * 
     * @return The time of the Individuo.
     */
    int getTime();

    /**
     * Gets the distribution of the Individuo.
     * 
     * @return The distribution of the Individuo.
     */
    String getDistribution();

    /**
     * Returns a string representation of the Individuo.
     * 
     * @return A string representation of the Individuo.
     */
    String toString();
}
