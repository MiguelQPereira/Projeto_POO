package dss;

import java.util.PriorityQueue;

public class Simulation implements Sim{
    
    private final int t;
    private final int u;
    private final int q;
    private final int s;

    private double instante;

    PriorityQueue<EventTimer> Evs;

    public Simulation(int t, int u, int q, int s) {

        this.t = t;
        this.u = u;
        this.q = q;
        this.s = s;
        this.instante = 0;

        this.Evs = new PriorityQueue<EventTimer>();
    }

    public boolean nextEv() {

        if (Evs.isEmpty()) return false;

        EventTimer next = Evs.poll();

        this.instante = next.time;

        next.ev.simulate();

        return true;
    }

    public void addEv(Event ev) {

        EventTimer n = new EventTimer(ev, u, q, s, this.instante);

        if (n.time > this.t || n.time < 0) return;

        //Comparator

        this.Evs.add(n);

        return;
    }

}
