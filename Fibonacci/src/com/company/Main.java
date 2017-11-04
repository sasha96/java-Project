package com.company;

public class Main {

    public static void main(String[] args) {
        int n;          // the index n for F(n), starting from n=3
        int fn = 0;             // F(n) to be computed
        int fnMinus1 = 1;   // F(n-1), init to F(2)
        int fnMinus2 = 1;   // F(n-2), init to F(1)
        int nMax = 20;      // maximum n, inclusive
        int sum = 0;  // Need sum to compute average
        double average;

        System.out.println("The first " + nMax + " Fibonacci numbers are:");
        System.out.println(fnMinus1);
        System.out.println(fnMinus2);
        for (n = 3; n <= nMax; n++) {
            fn = fnMinus1 + fnMinus2;
            fnMinus2 = fnMinus1;
            fnMinus1 = fn;
            System.out.println(fn);
            sum += fn;
        }
        average = sum / nMax;
        System.out.println(average);
    }
}
