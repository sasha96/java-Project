package com.company;

public class Main {

    public static void main(String[] args) {
	int min = 0 ;
	int max = 20;
	int var1 = 1;
	int var2 = 1;
	int var3 = 2;
	int result = 0;
	double avr = 0 ;
	int suma = 0;
        System.out.println("First " + max + " numbers Tribonacci");
        for (min = 0; min < max; min++) {
            result = var1 + var2 + var3 ;
            System.out.println(result);
            suma +=result;
            var1 = var2;
            var2 = var3;
            var3 = result;
        }
        avr =(double) suma/max;
        System.out.println(avr);
    }
}
