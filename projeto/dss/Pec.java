package dss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Pec implements PecInt {
    
    private static Pec pec = new Pec();

    private PriorityQueue<Event> q;

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

    public static Pec getPec() {

        return pec;
    }

    public Event getNext() {

        return this.q.poll();
    }

    public void add(Event ev) {

        this.q.add(ev);

        return;
    }

    public boolean isEmpty() {

        return this.q.isEmpty();
    }

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

    public void empties() {

        while (!q.isEmpty()) this.q.poll();

        return;
    }
}
