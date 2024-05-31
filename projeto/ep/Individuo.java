package ep;

public interface Individuo {
    Individuo rep();
    void mut();
    void dead();
    double getConfort();
    String toString();
}
