package sample.Problems.DynamicProgramming;

/*
Ugly numbers are numbers whose only prime factors are 2, 3 or 5.
The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers.
By convention, 1 is included.

Given a number n, the task is to find n’th Ugly number.

Examples:

Input  : n = 7
Output : 8

Input  : n = 10
Output : 12

Input  : n = 15
Output : 24

Input  : n = 150
Output : 5832
 */

import sample.Problems.Hard;

@Hard
public class UglyNumber {
    private int getNthUglyNumber(int n) {
        int[] uglyNumbers = new int[n + 1];

        int counter2 = 1;
        int counter3 = 1;
        int counter5 = 1;

        int uglyNumberMultipleOf2 = 2;
        int uglyNumberMultipleOf3 = 3;
        int uglyNumberMultipleOf5 = 5;

        uglyNumbers[1] = 1;

        for (int i = 2; i <= n; i++) {
            uglyNumbers[i] = Integer.min(uglyNumberMultipleOf2,
                    Integer.min(uglyNumberMultipleOf3, uglyNumberMultipleOf5));

            if (uglyNumbers[i] == uglyNumberMultipleOf2) {
                counter2++;
                uglyNumberMultipleOf2 = uglyNumbers[counter2] * 2;
            }
            if (uglyNumbers[i] == uglyNumberMultipleOf3) {
                counter3++;
                uglyNumberMultipleOf3 = uglyNumbers[counter3] * 3;
            }
            if (uglyNumbers[i] == uglyNumberMultipleOf5) {
                counter5++;
                uglyNumberMultipleOf5 = uglyNumbers[counter5] * 5;
            }
        }

        return uglyNumbers[n];
    }

    public static void main(String[] args) {
        System.out.println(new UglyNumber().getNthUglyNumber(10));
    }
}
