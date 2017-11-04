package com.company;

import java.util.Scanner;

import static java.lang.Math.PI;

public class Main {

    public static void main(String[] args) {
        boolean isTrue = true;
        while (isTrue) {
            Scanner sc = new Scanner(System.in);
            System.out.println("write please radios circle");
            double radius = sc.nextDouble();
            if (radius == -1.) {
                isTrue = false;
            } else {
                double area = radius * radius * Math.PI;
                double circumference = (PI + PI) * radius;
                System.out.println("radius " + radius);
                System.out.println("area " + area);
                System.out.println("circumference " + circumference);
            }
        }
    }
}
