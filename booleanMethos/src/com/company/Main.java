package com.company;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        int ke = 5;
        int mas[] = new int[4];
        mas[0] = -2;
        mas[1] = 5;
        mas[2] = 2;
        mas[3] = 20;
        m.contains(mas, ke);
    }

    private boolean b = false;

    public boolean contains(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                b = true;

                System.out.println(  b);
            } else {
                b = false;
            }

        }

        return b;
    }
}
