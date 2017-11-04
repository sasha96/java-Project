package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	Main m = new  Main();
	int array []= new int[7];
	array[0] =2;
	array[1] =5;
	array[2] =3;
	array[3] =3;
	array[4] =6;
	m.copyOf(array);
    }
    public int copyOf(int[] array){
      int a[] = Arrays.copyOf(array,array.length);
        for(int y:
                a) {
            System.out.println(y);
        }
      return a[array.length-1];

    }

}
