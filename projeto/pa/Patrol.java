package pa;

import java.util.Random;
import ep.Individuo;

public class Patrol implements Individuo{

    private final int n;
    private final int m;
    private int[][] matrix;
    private int[][] allocation;
    private double confort;
    private double time;
    private final double tmin;

    private double deadTime;

    public Patrol(int arg_n ,int arg_m, int[][] arg_c) {

        Random random = new Random();

        this.n = arg_n;
        this.m = arg_m;
        this.allocation = new int[n][m];
        this.matrix = arg_c;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = 0;
            }
        }

        for (int j = 0; j < m; j++) {
            int x = random.nextInt(n);
            allocation[x][m] = matrix[x][m];
        }

        this.tmin = calcTMin();

        calConfort();

        this.deadTime = 0;
    }

    private void calConfort () {

        this.calcTime();
        
        this.confort = this.tmin / this.time;

        if (this.confort < 0 || this.confort > 1) System.out.println("Erro calcConfort .|:|:|:|:|:|::|:|:|:|::|::|");

        return ;
    }

    private void calcTime() {

        double t_z = 0;

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

    public void dead() {

        return;
    }

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

    public String toString() {

        String str = String.format("{%s} : %d : %d", this.getDistribution(), this.time, this.confort);;

        return str;
    }

    public void setDeadTime(double t) {
        this.deadTime = t;

        return;
    }

    public double getDeadTime() {
        return this.deadTime;
    }

    public double getTime() {
        return this.time;
    }

    public String getDistribution() {

        StringBuilder patrulhas = new StringBuilder();

        for (int i = 0; i < this.n; i++) {

            patrulhas.append('{');
            for (int j = 0; j < this.m; j++) {

                if (this.allocation[i][j] != 0) patrulhas.append(j + 1);
                if (j != (this.m -1)) patrulhas.append(',');

            }
            patrulhas.append('}');

            if (i != (this.n -1)) patrulhas.append(',');
        }

        return patrulhas.toString();

    }

}
