package com.company;

public class Main {

    public static void main(String[] args) {
        int maxDenominator = 50000;  // Use a more meaningful name instead of n
        double sumL2R = 0.0;    // sum from left-to-right
        double sumR2L = 0.0;    // sum from right-to-left
        double result = 0.0;
        // for-loop for summing from left-to-right
        for (double denominator = 1; denominator <= maxDenominator; ++denominator) {
            sumL2R += 1 / denominator;
            // Beware that int/int gives int, e.g., 1/2 gives 0.
        }
        sumL2R += 1;
        System.out.println("The sum from left-to-right is: " + sumL2R);

        // for-loop for summing from right-to-left
        for (double i = maxDenominator; i > 1; i--) {
            sumR2L += 1 / i;
        }
        sumR2L += 1;
        System.out.println("The sum from right-to-left is: " + sumR2L);
        // Find the difference and display

        result = sumL2R - sumR2L;
        System.out.println(result);
    }
}
