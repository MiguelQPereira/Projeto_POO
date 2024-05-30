package ep;

import java.util.ArrayList;
import java.util.Iterator;
import dss.Sim;

public class Population implements Pop<Sim>{

    private final int maxPop;

    EvFactory fabMorte;
    EvFactory fabMut;
    EvFactory fabRep;
    EvFactory fabPrint;

    private ArrayList<Individuo> pop;
    private Iterator<Individuo> iterator;
    
    public Population(int maxPop) {
        this.maxPop = maxPop;

        this.pop = new ArrayList<Individuo>();
        this.iterator = this.pop.iterator();

        fabMorte = new MorteFactory();
        fabMut = new MutFactory();
        fabRep = new RepFactory();
        fabPrint = new PrintFactory();
    }

    public void addInd(Individuo ind, Sim s) {

        if (this.pop.size() > maxPop) {
            epidmia();
        }

        return;
    }

    public void remInd(Individuo ind) {

        while (iterator.hasNext()) {
            Individuo ele = iterator.next();
            if (ele.equals(ind)) {
                iterator.remove();
            }
        }

        return;
    }

    private void epidmia() {


        return;
    }

}
