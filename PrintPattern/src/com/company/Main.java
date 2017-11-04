package com.company;

public class Main {

    public static void main(String[] args) {
        int size = 7;
        char cha = '#';
        for (int i = size; i > 0; i--) {
            for (int j = size; j > 0; j--) {
                if (i == size || i == 1 || j == size || j == 1) {
                    System.out.print(cha + " ");
                } else if (i == j || i + j == size + 1) {
                    System.out.print(cha + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println("");

        }
    }
}
