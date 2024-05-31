package body;

import ep.Population;
import pa.Patrol;
import dss.Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        final int[] n = {0};
        final int[] m = {0};
        final int[] tau = {0};
        final int[] v = {0};
        final int[] vMax = {0};
        final int[] mu = {0};
        final int[] rho = {0};
        final int[] delta = {0};
        final int[][] C = null;

        getAgrs(args, n, m, tau, v, vMax, mu, rho, delta, C);

        
        Population pop = new Population(vMax[0]);
        Simulation sim = new Simulation(tau[0], mu[0], rho[0], delta[0]);

        for (int i = 0; i < v[0]; i++) {
            pop.addInd(new Patrol(n[0], m[0], C), sim);
        }

        while (sim.nextEv()) {
            if (pop.perfeito()) break;
        }

    }

    private static void getAgrs(String[] args, int[] n, int[] m, int[] tau, int[] v, int[] vMax, int[] mu, int[] rho, int[] delta, int[][] C) {

        if (args.length > 0) {
            if ("-r".equals(args[0])) {
                handleRandomMode(args, n, m, tau, v, vMax, mu, rho, delta, C);
            } else if ("-f".equals(args[0])) {
                handleFileMode(args, n, m, tau, v, vMax, mu, rho, delta, C);
            } else {
                System.out.println("Invalid command. Use -r for random mode or -f for file mode.");
            }
        } else {
            System.out.println("Usage:");
            System.out.println("Random mode: java -jar project.jar -r n m tau nu numax mu rho delta");
            System.out.println("File mode: java -jar project.jar -f <infile>");
        }

        return;

    }

    private static void handleRandomMode(String[] args, int[] n, int[] m, int[] tau, int[] v, int[] vMax, int[] mu, int[] rho, int[] delta, int[][] C){
        if (args.length != 9){
            System.out.println("Invalid number of parameters");
            System.out.println("Usage: java -jar project.jar -r n m tau nu numax mu rho delta");
            return;
        }

        n[0] = Integer.parseInt(args[1]);
        m[0] = Integer.parseInt(args[2]);
        tau[0] = Integer.parseInt(args[3]);
        v[0] = Integer.parseInt(args[4]);
        vMax[0] = Integer.parseInt(args[5]);
        mu[0] = Integer.parseInt(args[6]);
        rho[0] = Integer.parseInt(args[7]);
        delta[0] = Integer.parseInt(args[8]);

        C = generateRandomMatrix(n[0], m[0]);
        printMatrix(C);
    }

    private static void handleFileMode(String[] args, int[] n, int[] m, int[] tau, int[] v, int[] vMax, int[] mu, int[] rho, int[] delta, int[][] C) {
        if (args.length != 2) {
            System.out.println("Invalid number of parameters for file mode.");
            System.out.println("Usage: java -jar project.jar -f <infile>");
            return;
        }

        String filename = args[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] params = reader.readLine().split(" ");
            n[0] = Integer.parseInt(params[0]);
            m[0] = Integer.parseInt(params[1]);
            tau[0] = Integer.parseInt(params[2]);
            v[0] = Integer.parseInt(params[3]);
            vMax[0] = Integer.parseInt(params[4]);
            mu[0] = Integer.parseInt(params[5]);
            rho[0] = Integer.parseInt(params[6]);
            delta[0] = Integer.parseInt(params[7]);

            C = new int[n[0]][m[0]];
            for (int i = 0; i < n[0]; i++) {
                String[] row = reader.readLine().split(" ");
                for (int j = 0; j < m[0]; j++) {
                    C[i][j] = Integer.parseInt(row[j]);
                }
            }
            printMatrix(C);
            // Implement simulation logic using the matrix C and other parameters
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
    }

    private static int[][] generateRandomMatrix(int n, int m) {
        Random random = new Random();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = random.nextInt(10); 
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
