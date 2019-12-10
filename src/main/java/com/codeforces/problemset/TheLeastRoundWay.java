package com.codeforces.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TheLeastRoundWay {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int matrixSize = Integer.valueOf(in.readLine());
        int[][] checker1 = new int[matrixSize][matrixSize];
        int[][] checker2 = new int[matrixSize][matrixSize];
        int k = -1;
        String[] matrixArray = new String[matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            matrixArray = in.readLine().split(" ");
            for (int j = 0; j < matrixSize; j++) {
                int temp = Integer.parseInt(matrixArray[j]);
                if (temp == 0) {
                    k = i;
                    checker1[i][j] = 10;
                    checker2[i][j] = 10;
                }
                checker1[i][j] = factor(temp, 2);// get factors instead of trying to mod every number
                checker2[i][j] = factor(temp, 5);
            }
        }

        for (int i = 1; i < matrixSize; i++) {
            checker1[0][i] += checker1[0][i-1];
            checker1[i][0] += checker1[i-1][0];
            checker2[0][i] += checker2[0][i-1];
            checker2[i][0] += checker2[i-1][0];
        }

        for (int i = 1; i < matrixSize; i++){
            for (int j = 1; j < matrixSize; j++){
                checker1[i][j] += min(checker1[i][j-1], checker1[i-1][j]);
                checker2[i][j] += min(checker2[i][j-1], checker2[i-1][j]);
            }
        }

        int first = matrixSize - 1; // row
        int second = matrixSize - 1; // column

        int choose1 = checker1[matrixSize - 1][matrixSize - 1];
        int choose2 = checker2[matrixSize - 1][matrixSize - 1];
        int[][] choice;

        if (choose1 < choose2){
            choice = checker1;
        }
        else {
            choice = checker2;
        }

        int trailingZeros = min(choose1, choose2);// 尾随0
        if (k != -1 && trailingZeros > 1) {// different path
            out = new StringBuilder();
            trailingZeros = 1;
            for (int i = 0; i < k; i++) {
                out.append("D");
            }
            for (int i = 0; i < matrixSize - 1; i++) {
                out.append("R");
            }
            for (int i = k; i < matrixSize - 1; i++) {
                out.append("D");
            }
        } else {
            while (!(first == 0 && second == 0)) {
                if (first == 0) {
                    second--;
                    out.insert(0, "R");
                } else if (second == 0) {
                    first--;
                    out.insert(0, "D");
                } else {
                    if (choice[first][second - 1] <= choice[first - 1][second]) {
                        second--;
                        out.insert(0, "R");
                    } else {
                        first--;
                        out.insert(0, "D");
                    }
                }
            }
        }
        System.out.print(trailingZeros + "\n" + out);
    }

    public static int min(int left, int right) {
        if (left < right) {
            return left;
        }
        else{
            return right;
        }
    }

    static int factor(int num, int factorer) {
        if (num == 0) {
            return num;
        }
        int val = 0;
        while (num % factorer == 0) {
            val++;
            num /= factorer;
        }
        return val;
    }
}

