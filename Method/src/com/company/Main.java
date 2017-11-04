package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        int mas[] = new int[1];
        m.printArray(mas);
        Main m2 = new Main();
        double mas2[] = new double[2];
        m.printArray(mas2);
    }

    public void printArray(int[] array) {
        if (array.length == 0) {
            System.out.println("your array are empty");
        } else {
            for (int a : array) {
                System.out.println(a);
            }
        }
    }

    public void printArray(double[] array) {
        if (array.length == 0) {
            System.out.println("your array are empty");
        } else {
            for (double a : array) {
                System.out.println(a);
            }
        }
    }


}
