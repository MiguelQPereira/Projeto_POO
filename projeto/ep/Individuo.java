package ep;

public interface Individuo {
    Individuo rep();
    void mut();
    void dead();
    double getConfort();
    void setDeadTime(double t);
    double getDeadTime();
    int getTime();
    String getDistribution();
    String toString();
}
