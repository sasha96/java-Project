package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next().toLowerCase();
        System.out.println("you enter " + result);
        int countInt = 0;
        int countString = 0;
        double suma = result.length();

        for (int i = 0; i < result.length(); i++) {
            int c = result.charAt(i);
            if (c >= '0' && c <= '9') {
                countInt++;
            }
        }
        double integ = countInt / suma * 100;
        System.out.println("number of vowels " + countInt +":" + integ);
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == 'a' || result.charAt(i) == 'e' || result.charAt(i) == 'u' || result.charAt(i) == 'i' || result.charAt(i) == 'o') {
                countString++;
            }
        }
        double str = countString / suma * 100;
        System.out.println("number of digits " + countString + ":" + str);
    }
}
