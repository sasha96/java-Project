package com.company;

public class Main {

    public static void main(String[] args) {
	Main m = new Main();
	int array[] = {2,12,4,3,45};
	m.reversArrays(array);
    }
    public int reversArrays(int array[]){
        for (int i = 0; i <= array.length/2; i++) {
            int w = 0;
            w = array[i] ;
             array[i] = array[array.length-i-1];
            array[array.length-i-1]=w;
        }for (int y:
                array) {
            System.out.print(y+" ");
        }
        return array[3];
    }
}
