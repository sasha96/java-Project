package com.company;

public class Main {

    public static void main(String[] args) {

        int number = 12345;
        int n = 5;
        int digit = 0;
        String re = "";
        while (n > 0) {
            digit = number % 10;
            re += digit + " ";
            number = number / 10;
            n--;
        }
        System.out.println(re);
    }
}
