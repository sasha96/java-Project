package com.company;

public class Main {

    public static void main(String[] args) {
        int var = 12;
        int sum;
        System.out.println("* "+" | " + 1 + " " + 2 + " " + 3 + " " + +4 + " " + 5 + " " + 6 + " " + 7 + " " + 8 + " " + 9 + " " + 10 + " " + 11 + " " + 12 + " ");
        System.out.println("-------------------------------");
        for (int i = 1; i <= var; i++) {
            System.out.print(i + "  |  ");
            for (int j = 1; j <= var; j++) {

                sum = i * j;
                System.out.print(sum + " ");
            }
            System.out.println(" ");
        }
    }
}
