package com.gqs.example;

import java.util.Arrays;

/**
 * 描述:
 *
 * @author 郭乔森
 * @create 2019-06-16 15:35
 */
public class demo {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 2, 8};
        int[] array = null;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 8) {
                array = new int[i];
                System.arraycopy(arr, 0, array, 0, i);
            }
        }

        /*for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                System.out.println(array[i] + " " + array[j]);
                for (int k = j + 1; k < array.length; k++) {
                    System.out.println(array[i] + " " + array[j] + " " + array[k]);
                    for (int x = k + 1; x < array.length; x++) {
                        System.out.println(array[i] + " " + array[j] + " " + array[k] + " " + array[x]);
                    }
                }
            }
        }*/

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------");

        digui(0, 1, array);
    }

    private static void digui(int n, int m, int[] array) {

        for (int i = n + 1; i < array.length; i++) {
            System.out.print(array[n] + "");
            System.out.print(array[i] + "");
//            if()
            System.out.println();
        }


        m++;
        digui(++n, m, array);
    }
}
/*
12
13
14
15
23
24
25
34
35
45
123
124
125
134
135
145
234
235
245
345
1234
1235
1245
1345
2345
12345


















*/