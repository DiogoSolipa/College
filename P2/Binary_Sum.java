package com.company;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        int[] n1 = new int[3];
        int[] n2 = new int[3];
        int[] n3 = new int[3];

        Scanner sc = new Scanner(System.in);

        n1[2] = sc.nextInt();
        n2[2] = sc.nextInt();

        for (int i = 2; i >= 0; i--) {


            if (n1[i] + n2[i] > 1) {
                n3[i] = 0;

            } else if (n1[i] + n2[i] == 0) {
                n3[i] = 0;
            } else if (n1[i] + n2[i] == 1) {
                n3[i] = 1;
            }
        }

        System.out.println(Arrays.toString(n3));

    }
}

// public static Cha [] ---> int [];


