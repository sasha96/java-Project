package com.company;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        int array[] = {2,12,4,3,45};
        int array2[] = {12,1,43,31,5};
       m.swapArrays(array,array2);
        for (int w:
             array) {
            System.out.print(w + " ");
        }
        System.out.println("");
        for (int w:
                array2) {
            System.out.print(w + " ");
        }
    }
    public int swapArrays(int array[],int array2[]){
        int var = 0;
        for (int i = 0; i < array.length; i++) {
            var = array[i];
            array[i]=array2[i];
            array2[i]=var;
        }
        return array[2];
    }
}
