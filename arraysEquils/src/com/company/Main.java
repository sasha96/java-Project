package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int arrays[]= new int[5];
        int arrays2[]= new int[5];
        arrays[2]=2;

        Main m = new Main();
        m.arraysEquals(arrays,arrays2);
    }

    public int[] arraysEquals(int[] array, int[] array2){
        int arrays3 [] = new int[array.length];
        for (int i = 0; i <array.length ; i++) {

            if (array.length==array2.length){
                System.out.println("length is equals");
            }
            if (array[i]==array2[i]){
                arrays3[i]++;
            }

        }
        for (int t:
                arrays3 ) {
            System.out.println(t);
        }

        return array ;
    }
}
