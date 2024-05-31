package pa;

import java.util.Random;
import ep.Individuo;

public class Patrol implements Individuo{

    private final int n;
    private final int m;
    private int[][] matrix;
    private int[][] allocation;
    private double confort;
    private final double tmin;

    public Patrol(int arg_n ,int arg_m, int[][] arg_c) {

        Random random = new Random();

        n = arg_n;
        m = arg_m;
        allocation = new int[n][m];
        matrix = arg_c;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = 0;
            }
        }

        for (int j = 0; j < m; j++) {
            int x = random.nextInt(n);
            allocation[x][m] = matrix[x][m];
        }

        tmin = calcTMin();

        confort = calConfort();
    }

    private double calConfort () {

        double t_z = 0;

        for (int i = 0, sum = 0; i < this.n; i++, sum = 0) {
            for (int j = 0; j < this.m; j++) {
                    sum = sum + this.allocation[i][j];
            }
            if (t_z < sum) {
                t_z = sum;
            }
        }

        return this.tmin / t_z;
    }

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

    public double getConfort() {

        return this.confort;
    }

}
