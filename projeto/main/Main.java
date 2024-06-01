package main;

import ep.Population;
import pa.Patrol;
import dss.Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * The Main class serves as the start for the application.
 * It initializes the parameters for the simulation, sets up the simulation 
 * and executes it.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     * 
     * @param args Command line arguments specifying the mode and parameters for the simulation.
     */
    public static void main(String[] args) {

        final int[] n = {0};
        final int[] m = {0};
        final int[] tau = {0};
        final int[] v = {0};
        final int[] vMax = {0};
        final int[] mu = {0};
        final int[] rho = {0};
        final int[] delta = {0};
        final int[][][] C = {null};

        getAgrs(args, n, m, tau, v, vMax, mu, rho, delta, C);

        
        Simulation sim = new Simulation(tau[0]);
        Population pop = new Population(vMax[0], sim, mu[0], rho[0], delta[0]);

        for (int i = 0; i < v[0]; i++) {
            pop.addInd(new Patrol(n[0], m[0], C[0]));
        }

        sim.runSim();

    }

    /**
     * Parses the command line arguments and initializes the simulation parameters.
     * Verifies if the program was called correctly.
     * 
     * @param args Command line arguments.
     * @param n Number of patrols.
     * @param m Number of planetary systems.
     * @param tau Total simulation time.
     * @param v Initial population size.
     * @param vMax Maximum population size.
     * @param mu Death rate.
     * @param rho Reproduction rate.
     * @param delta Mutation rate.
     * @param C Cost matrix for patroling planetary systems.
     */
    private static void getAgrs(String[] args, int[] n, int[] m, int[] tau, int[] v, int[] vMax, int[] mu, int[] rho, int[] delta, int[][][] C) {

        if (args.length > 0) {
            if ("-r".equals(args[0])) {
                handleRandomMode(args, n, m, tau, v, vMax, mu, rho, delta, C);
            } else if ("-f".equals(args[0])) {
                handleFileMode(args, n, m, tau, v, vMax, mu, rho, delta, C);
            } else {
                System.out.println("Invalid command. Use -r for random mode or -f for file mode.");
                System.exit(0);
            }
        } else {
            System.out.println("Usage:");
            System.out.println("Random mode: java -jar project.jar -r n m tau nu numax mu rho delta");
            System.out.println("File mode: java -jar project.jar -f <infile>");
            System.exit(0);
        }

        return;

    }

    /**
     * Handles the random mode by genetating a random cost Matrix for the simulation.
     * 
     * @param args Command line arguments.
     * @param n Number of patrols.
     * @param m Number of planetary systems.
     * @param tau Total simulation time.
     * @param v Initial population size.
     * @param vMax Maximum population size.
     * @param mu Death rate.
     * @param rho Reproduction rate.
     * @param delta Mutation rate.
     * @param C Cost matrix for patroling planetary systems.
     */
    private static void handleRandomMode(String[] args, int[] n, int[] m, int[] tau, int[] v, int[] vMax, int[] mu, int[] rho, int[] delta, int[][][] C){
        if (args.length != 9){
            System.out.println("Invalid number of parameters");
            System.out.println("Usage: java -jar project.jar -r n m tau nu numax mu rho delta");
            System.exit(0);
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

        C[0] = generateRandomMatrix(n[0], m[0]);
    }

    /**
     * Handles the file mode by reading the simulation parameters from a sepecified input file.
     * 
     * @param args Command line arguments.
     * @param n Number of patrols.
     * @param m Number of planetary systems.
     * @param tau Total simulation time.
     * @param v Initial population size.
     * @param vMax Maximum population size.
     * @param mu Mutation rate.
     * @param rho Reproduction rate.
     * @param delta Death rate.
     * @param C Cost matrix for patroling planetary systems.
     */
    private static void handleFileMode(String[] args, int[] n, int[] m, int[] tau, int[] v, int[] vMax, int[] mu, int[] rho, int[] delta, int[][][] C) {
        if (args.length != 2) {
            System.out.println("Invalid number of parameters for file mode.");
            System.out.println("Usage: java -jar project.jar -f <infile>");
            System.exit(0);
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

            C[0] = new int[n[0]][m[0]];
            for (int i = 0; i < n[0]; i++) {
                String[] row = reader.readLine().split(" ");
                for (int j = 0; j < m[0]; j++) {
                    C[0][i][j] = Integer.parseInt(row[j]);

                    if (C[0][i][j] <= 0) {
                        System.out.println("Invalid file matrix.");
                        System.out.println("Matrix must have only positive integers.");
                        System.exit(0);
                    }

                }
            }
            // Implement simulation logic using the matrix C and other parameters
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            System.exit(0);
        }
    }
    /**
     * Generats a random matrix for the cost of patroling the planetary systems.
     * 
     * @param n Number of patrols.
     * @param m Numbre of planetary systems.
     * @return matrix of size n x m with random positive integers.
     */
    private static int[][] generateRandomMatrix(int n, int m) {
        Random random = new Random();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = random.nextInt(10) + 1; 
            }
        }
        return matrix;
    }
}
