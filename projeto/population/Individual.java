package population;


public class Individual {
    private int patrols;
    private int planetarySys;
    private double comfort;
    private int[][] timeMatrix;

    public Individual(int n, int m, double comfort, int[][] C){
        this.patrols = n;
        this.planetarySys = m;
        this.comfort = comfort;
        this.timeMatrix = C;
    }    
    public double getComfort(){
        return comfort;
    }

    public double updateComfort(double newComfort){
        comfort = newComfort;
        return comfort;
    }

}
