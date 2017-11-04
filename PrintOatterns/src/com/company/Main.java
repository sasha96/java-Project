package com.company;

public class Main {

    public static void main(String[] args) {
        Main.printPattern(8);
    }

    public static void printPattern(int size) {
        for (int i = 1; i  <=size; i++) {
            for (int j = size; j >=1; j--) {
                if (i>j) {
                    System.out.print(" ");

                }else {
                    System.out.print(j-i+1);
                }
            }
            System.out.println(" ");

        }
    }
}
