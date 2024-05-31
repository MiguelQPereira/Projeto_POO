package dss;

public interface PecInt {

    Event getNext();
    void add(Event ele);
    boolean isEmpty();
    void clean();
    void empties();

}
