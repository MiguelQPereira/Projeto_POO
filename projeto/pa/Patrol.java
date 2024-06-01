package pa;

import java.util.Random;
import ep.Individuo;

/**
 * The Patrol class implements the Individuo interface and represents a patrol unit.
 * It manages the allocation of patrols, calculates comfort levels, and supports mutation and replication of patrols.
 */
public class Patrol implements Individuo{
    
    /** Number of rows. */
    private final int n;

    /** Number of columns. */
    private final int m;

    /** Matrix representing the patrol areas. */
    private int[][] matrix;

    /** Allocation matrix for patrol units. */
    private int[][] allocation;

    /** Comfort level of the patrol. */
    private double confort;

    /** Time required for patrol coverage. */
    private int time;

    /** Minimum required time for patrol coverage. */
    private final double tmin;

    /** Time at which the patrol is marked as dead. */
    private double deadTime;

    /**
     * Constructs a Patrol instance with the given parameters.
     *
     * @param arg_n Number of rows.
     * @param arg_m Number of columns.
     * @param arg_c Matrix representing patrol areas.
     */
    public Patrol(int arg_n ,int arg_m, int[][] arg_c) {

        Random random = new Random();

        this.n = arg_n;
        this.m = arg_m;
        this.allocation = new int[this.n][this.m];
        this.matrix = arg_c;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = 0;
            }
        }

        for (int j = 0; j < m; j++) {
            int x = random.nextInt(n);
            //System.out.println("j: " + j + " n: " + n + " x: " + x);
            allocation[x][j] = matrix[x][j];
        }

        this.tmin = calcTMin();

        calConfort();

        this.deadTime = 0;
    }

    /**
     * Calculates the comfort level of the patrol.
     */
    private void calConfort () {

        this.calcTime();
        
        this.confort = this.tmin / this.time;

        if (this.confort < 0 || this.confort > 1) System.out.println("Erro calcConfort .|:|:|:|:|:|::|:|:|:|::|::|");

        return ;
    }

    /**
     * Calculates the time required for patrol coverage.
     */
    private void calcTime() {

        int t_z = 0;

        for (int i = 0, sum = 0; i < this.n; i++, sum = 0) {
            for (int j = 0; j < this.m; j++) {
                    sum = sum + this.allocation[i][j];
            }
            if (t_z < sum) {
                t_z = sum;
            }
        }

        this.time = t_z;

        return;
    }

    /**
     * Calculates the minimum required time for patrol coverage.
     *
     * @return The minimum required time for patrol coverage.
     */
    private double calcTMin() {

        double tmin = 0;

        for (int j = 0, min = Integer.MAX_VALUE; j < this.m; j++, min = Integer.MAX_VALUE) {
            for (int i = 0; i < this.n; i++) {
                if (this.matrix[i][j] < min) {
                    min = this.matrix[i][j];
                }
            }
            tmin = tmin + min;
        }

        tmin = tmin / this.n;

        return tmin;
    }

    /**
     * Gets the comfort level of the patrol.
     *
     * @return The comfort level of the patrol.
     */
    public double getConfort() {

        return this.confort;
    }

    /**
     * Marks the patrol as dead. (Does nothing)
     */
    public void dead() {

        return;
    }

    /**
     * Mutates the patrol allocation by randomly reassigning patrols.
     */
    public void mut() {

        //Caso só haja uma patrulha
        if (this.n < 2) return;

        Random random = new Random();

        int sistema;
        int i;
        int sum;
        int aux;
        int aux2;

        while (true) {
            i = random.nextInt(n);

            //Confirma se a patrulha tem algum sistema atribuido
            for (aux=0, sum = 0; aux < this.m; aux ++) {
                if (this.allocation[i][aux] != 0) sum++;
            }
            if (sum != 0) break;
            
        }

        //Determina o sistema a alterar
        sistema = random.nextInt(sum);

        for (aux = 0 ,aux2 = 0; aux < this.m ; aux++) {

            if (this.allocation[i][aux] != 0) aux2++;

            if (aux2 == (sistema + 1)) {

                sistema = aux;

                break;
            }
        }

        //Troca o sistema de patrulha
        this.allocation[i][sistema] = 0;

        int j;
        for (j = random.nextInt(n); j == i; j = random.nextInt(n));

        this.allocation[j][sistema] = this.matrix[j][sistema];

        this.calConfort();

        return;
    }

    /**
     * Replicates the patrol, creating a new patrol instance with similar attributes.
     *
     * @return A new replicated Patrol instance.
     */
    public Individuo rep() {

        Random random = new Random();

        Patrol nova = new Patrol(this.n, this.m, this.matrix);

        //Cria um clone
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                nova.allocation[i][j] = this.allocation[i][j];
            }
        }

        nova.calConfort();

        if (this.n == 1) return nova;

        //remove os sistemas
        double remover = (1 - nova.confort) * nova.m;

        for (int j = 0, rem = 0, sum = 0, i = 0; rem < remover; j++) {

            if (j >= m) j = 0;

            for (i = 0, sum = 0; i < n; i++) {
                sum = sum + nova.allocation[i][j];
            }

            if (sum == 0) continue; //Sistema j não tem patrulha alocada

            if (random.nextInt(2) == 1) continue; //Não apaga a patrulha

            for (i = 0; i < n; i++) {
                nova.allocation[i][j] = 0;
            }

            rem++;
        }

        //realocar as patrulhas
        for (int j = 0, i = 0, sum = 0; j < nova.m; j ++) {
            for (i = 0, sum = 0; i < n; i++) {
                sum = sum + nova.allocation[i][j];
            }

            if (sum != 0) continue; //Sistema j já tem patrulha

            int rand = random.nextInt(n);

            nova.allocation[rand][j] = nova.matrix[rand][j];
        }

        nova.calConfort();

        return nova;
    }

    /**
     * Returns a string representation of the patrol, including its distribution, time, and comfort.
     *
     * @return A string representation of the patrol.
     */
    public String toString() {

        String str = String.format("{%s} : %d : %.3f", this.getDistribution(), this.time, this.confort);;

        return str;
    }

    /**
     * Sets the time at which the patrol is marked as dead.
     *
     * @param t The dead time to set.
     */
    public void setDeadTime(double t) {
        this.deadTime = t;

        return;
    }

    /**
     * Gets the time at which the patrol is marked as dead.
     *
     * @return The dead time of the patrol.
     */
    public double getDeadTime() {
        return this.deadTime;
    }

    /**
     * Gets the time required for patrol coverage.
     *
     * @return The time required for patrol coverage.
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Gets the distribution of patrols.
     *
     * @return The distribution of patrols.
     */
    public String getDistribution() {

        StringBuilder patrulhas = new StringBuilder();

        for (int i = 0; i < this.n; i++) {

            patrulhas.append('{');
            for (int j = 0 ,aux = 0; j < this.m; j++) {

                if (this.allocation[i][j] == 0) continue;
                if (aux != 0) patrulhas.append(',');
                patrulhas.append(j + 1);
                aux++;

            }
            patrulhas.append('}');

            if (i != (this.n -1)) patrulhas.append(',');
        }

        return patrulhas.toString();

    }

}
