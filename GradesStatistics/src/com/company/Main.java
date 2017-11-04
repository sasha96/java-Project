package com.company;

import java.util.Scanner;

public class Main {

    public static int[] grades = new int [5];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            grades[i] = scanner.nextInt();
        }
        Main m = new Main();
        m.average(grades);
        m.min(grades);
        m.max(grades);

    }
    public  void average(int arr []){
        int a = 0;
        for (int i = 0; i <arr.length ; i++) {
            a += arr[i];
        }
        System.out.println(a/arr.length);
    }
    public  void min(int arr []){
        int a = 100;
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i]<a){
                a=arr[i];
            }
        }
        System.out.println(a);
    }
    public  void max(int arr []){
        int a = 0;
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i]>a){
                a=arr[i];
            }
        }
        System.out.println(a);
    }




}
