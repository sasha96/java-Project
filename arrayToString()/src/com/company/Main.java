package com.company;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        int array[] = new int[2];
        array[0] = 1324;
        m.arrayToString(array);
    }

    public String arrayToString(int[] array) {
        String s = "";
        for (int i = 0; i < array.length; i++) {
            s += Integer.toString(array[i]);
            System.out.println(s);
            s = "";
        }
        return s;
    }
}

