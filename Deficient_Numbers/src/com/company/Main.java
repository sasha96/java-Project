package com.company;

public class Main {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i <100 ; i++) {
            for (int j = 1; j <i ; j++) {
                if (i%j==0){
                    count +=j;
                }
            }
            if (count<i){
                System.out.println("define " + i);

            }
            count = 0;
        }
    }
}
