package population;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private int vmax;
    private List<Individual> v;

    //Population constructor
    public Population(int vmax){
        this.vmax = vmax;
        this.v = new ArrayList<>();
    }

    //Methods to get inviduals
    public List<Individual> getIndividuals(){
        return v;
    }

}
