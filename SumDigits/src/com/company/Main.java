package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        int mas[] = new int[4];
        for (int i = 0; i < mas.length; i++) {
            int r = result % 10;
            result = result / 10;
            System.out.println(r);
            mas[i] = r;
        }
        int suma = 0;
        for (int i = 0; i <mas.length ; i++) {
            suma += mas[i];
        }
        System.out.println(suma);

    }
}
