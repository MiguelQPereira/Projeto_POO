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
            System.out.println("Random mode: java -jar project.jar -r n m τ ν νmax µ ρ δ");
            System.out.println("File mode: java -jar project.jar -f <infile>");
        }
    }

    private static void handleRandomMode(String[] args){
        if (args.length != 9){
            System.out.println("Invalid number of parameters");
            System.out.println("Usage: java -jar project.jar -r n m τ ν νmax µ ρ δ");
            return;
        }

        int n = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[2]);
        int tau = Integer.parseInt(args[3]);
        int nu = Integer.parseInt(args[4]);
        int nuMax = Integer.parseInt(args[5]);
        double mu = Double.parseDouble(args[6]);
        double rho = Double.parseDouble(args[7]);
        double delta = Double.parseDouble(args[8]);

        int[][] C = generateRandomMatrix(n, m);
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
            int n = Integer.parseInt(params[0]);
            int m = Integer.parseInt(params[1]);
            int tau = Integer.parseInt(params[2]);
            int nu = Integer.parseInt(params[3]);
            int nuMax = Integer.parseInt(params[4]);
            double mu = Double.parseDouble(params[5]);
            double rho = Double.parseDouble(params[6]);
            double delta = Double.parseDouble(params[7]);

            int[][] C = new int[n][m];
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

