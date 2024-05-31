package dss;

public class Simulation implements Sim{
    
    private final int t;
    private int numEvs;

    private double instante;

    private PecInt Evs;

    public Simulation(int t) {

        this.t = t;
        this.instante = 0;
        this.Evs = Pec.getPec();
    }

    public void runSim() {

        while (!Evs.isEmpty()) {

            Event next = Evs.getNext();

            this.instante = next.getTime();
    
            next.simulate();

            this.numEvs++;
        }

        return ;
    }

    public void addEv(Event ev) {

        if (ev.getTime() > this.t || ev.getTime() < 0) return;

        this.Evs.add(ev);

        return;
    }

    public double getTime() {
        return this.instante;
    }

    public int getMaxTime() {
        return this.t;
    }

    public int getEvsNum() {
        return this.numEvs;
    }

    public void emptiesPec() {

        this.Evs.empties();

        return;
    }

    public void cleanPec() {

        this.Evs.clean();

        return;
    }
}
