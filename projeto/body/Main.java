package body;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main{
    public static void main(String[] args){
        if (args.length > 0) {
            if ("-r".equals(args[0])) {
                handleRandomMode(args);
            } else if ("-f".equals(args[0])) {
                handleFileMode(args);
            } else {
                System.out.println("Invalid command. Use -r for random mode or -f for file mode.");
            }
        } else {
            System.out.println("Usage:");
            System.out.println("Random mode: java -jar project.jar -r n m tau nu numax mu rho delta");
            System.out.println("File mode: java -jar project.jar -f <infile>");
        }

        
    }

    private static void handleRandomMode(String[] args){
        if (args.length != 9){
            System.out.println("Invalid number of parameters");
            System.out.println("Usage: java -jar project.jar -r n m tau nu numax mu rho delta");
            return;
        }

        final int n = Integer.parseInt(args[1]);
        final int m = Integer.parseInt(args[2]);
        final int tau = Integer.parseInt(args[3]);
        final int nu = Integer.parseInt(args[4]);
        final int nuMax = Integer.parseInt(args[5]);
        final double mu = Double.parseDouble(args[6]);
        final double rho = Double.parseDouble(args[7]);
        final double delta = Double.parseDouble(args[8]);

        final int[][] C = generateRandomMatrix(n, m);
        printMatrix(C);
    }

    private static void handleFileMode(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid number of parameters for file mode.");
            System.out.println("Usage: java -jar project.jar -f <infile>");
            return;
        }

        String filename = args[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] params = reader.readLine().split(" ");
            final int n = Integer.parseInt(params[0]);
            final int m = Integer.parseInt(params[1]);
            final int tau = Integer.parseInt(params[2]);
            final int nu = Integer.parseInt(params[3]);
            final int nuMax = Integer.parseInt(params[4]);
            final double mu = Double.parseDouble(params[5]);
            final double rho = Double.parseDouble(params[6]);
            final double delta = Double.parseDouble(params[7]);

            final int[][] C = new int[n][m];
            for (int i = 0; i < n; i++) {
                String[] row = reader.readLine().split(" ");
                for (int j = 0; j < m; j++) {
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

