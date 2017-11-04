package com.company;

public class Main {

    public static void main(String[] args) {
        int suma = 0;
        int avr = 0;
        int num = 100;
        for (int i = 0; i <= num; i++) {
            suma = suma + i * i;
        }
        avr = suma / num;
        System.out.println("Cума всих чисел в квадраті " + suma + " Їхнє середнє значення " + avr);
    }
}
