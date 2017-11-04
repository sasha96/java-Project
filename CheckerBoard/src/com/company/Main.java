package com.company;

public class Main {

    public static void main(String[] args) {
        int size = 7;
        for (int row = 0; row < size; row++) {
            for (int cols = 0; cols < size; cols++) {
                if (row % 2 == 0) {
                    System.out.print("#");
                }
                System.out.print(" ");
                if (row % 2 != 0) {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
}
