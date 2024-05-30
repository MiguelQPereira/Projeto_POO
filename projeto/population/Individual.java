package population;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


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

    
    private double exponencialDistribution(){
        return ThreadLocalRandom.current().nextDouble();
    }

    public double getDeathInterval(double comfort, double mu){
        //calcula média da expressão fornecida
        double mean = (1-Math.log(1-comfort))*mu;
        
        //gera número aleatório uniformemente distribuido entre 0 e 1  
        double exp = exponencialDistribution();

        //aplica distribuição exponencial com a média
        return -Math.log(1-exp)*mean;
    }   

    public double getReproductionInterval(double comfort, double rho){
        //calcula média da expressão fornecida
        double mean = (1-Math.log(comfort))*rho;

        //gera número aleatório uniformemente distribuido entre 0 e 1  
        double exp = exponencialDistribution();

        //aplica distribuição exponencial com a média
        return -Math.log(1-exp)*mean;
    } 

    public double getMutationInterval(double comfort, double delta){
        //calcula média da expressão fornecida
        double mean = (1-Math.log(comfort))*delta;

        //gera número aleatório uniformemente distribuido entre 0 e 1  
        double exp = exponencialDistribution();

        //aplica distribuição exponencial com a média
        return -Math.log(1-exp)*mean;
    }
}
