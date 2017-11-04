package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введіть число студентів");
        int result = scanner.nextInt();
        int avr = 0;
        for (int i = 1; i <= result; i++) {
            System.out.println("Введіть оцінку для студента " + i);
            int grade = scanner.nextInt();
            if (grade < 100 && grade > 0) {
                System.out.println("enter the grade for student " + i +":"+ grade);
                avr += grade;
            }
            else {
                System.out.println("problem");
                i--;
            }
        }
        System.out.println("arerage : " + avr/result);

    }
}
