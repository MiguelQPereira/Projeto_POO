package ep;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import dss.Sim;

public class Population implements Pop<Sim>{

    private final int maxPop;

    EvFactory fabMorte;
    EvFactory fabMut;
    EvFactory fabRep;
    EvFactory fabPrint;

    private ArrayList<Individuo> pop;
    private ListIterator<Individuo> iterator;
    
    public Population(int maxPop) {
        this.maxPop = maxPop;

        this.pop = new ArrayList<Individuo>();
        this.iterator = this.pop.listIterator();

        fabMorte = new MorteFactory();
        fabMut = new MutFactory();
        fabRep = new RepFactory();
        fabPrint = new PrintFactory();
    }

    public boolean perfeito() {

        if ((this.pop.get(0)).getConfort() == 1) {

            //Print

            return true;
        }

        return false;
    }

    public void addInd(Individuo ind, Sim s) {

        //Ordenar População

        //Add Evs

        if (this.pop.size() > maxPop) {
            epidmia();
        }

        return;
    }

    public void remInd(Individuo ind) {

        Individuo ele;

        while (iterator.hasNext()) {
            ele = iterator.next();
            if (ele.equals(ind)) {

                //Apagar eventos quando individuo morre

                iterator.remove();
            }
        }

        return;
    }

    private void epidmia() {

        if (pop.size() < 6) return;

        Random random = new Random();
        double randomVariable;
        Individuo ele;

        this.iterator = this.pop.listIterator(5);

        while (iterator.hasNext()) {
            ele = iterator.next();

            randomVariable = random.nextDouble();

            if (randomVariable > (ele.getConfort() * 2 / 3)) remInd(ele);
        }

        return;
    }

}
