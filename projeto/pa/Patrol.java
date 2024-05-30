package pa;

import java.util.Random;
import ep.Individuo;

public class Patrol implements Individuo{

    private final int n;
    private final int m;
    private final int[][] allocation;
    private final double confort;

    public Patrol(int arg_n ,int arg_m, int[][] arg_c) {

        Random random = new Random();

        n = arg_n;
        m = arg_m;
        allocation = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = 0;
            }
        }

        for (int j = 0; j < m; j++) {
            int x = random.nextInt(n);
            allocation[x][m] = arg_c[x][m];
        }

        confort = calConfort(n, m, allocation);
    }

    private double calConfort (int n, int m, int[][] a) {

        double t_min = 0;
        int t_z = 0;

        int t = 0;

        for (int j = 0, min = Integer.MAX_VALUE; j < m; j++, min = Integer.MAX_VALUE) {
            for (int i = 0; i < n; i++) {
                if (allocation[i][j] < min) {
                    min = allocation[i][j];
                }
            }
            t = t + min;
        }

        t_min = t / n;

        for (int i = 0, sum = 0; i < m; i++, sum = 0) {
            for (int j = 0; j < n; j++) {
                    sum = sum + allocation[i][j];
            }
            if (t_z < sum) {
                t_z = sum;
            }
        }

        return t_min / t_z;
    }

}
