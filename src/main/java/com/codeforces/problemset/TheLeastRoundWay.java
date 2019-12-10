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

        }
    }

    public static int min(int left, int right){
        if (left < right){
            return left;
        }
        else{
            return right;
        }
    }

    static int factor(int num, int factorer) {
        if (num == 0){
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

/*
import java.io.BufferedReader;
        import java.io.InputStreamReader     import java.io.IOException;
        import java.io.InputStream;;

public class Application {

    public static void main(String[] args) throws IOException {
        handle(System.in);
    }

    private static void handle(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        int n = Integer.valueOf(reader.readLine());

        int[][] m2 = new int[n][n];
        int[][] m5 = new int[n][n];

        boolean[][] p2 = new boolean[n][n];
        boolean[][] p5 = new boolean[n][n];

        boolean hasZero = false;
        int zeroRow = 0, zeroCol = 0;

        for (int i = 0; i < n; i++) {
            int[] tmp = parse(reader.readLine(), n);
            for (int j = 0; j < n; j++) {
                int[] val25 = get25(tmp[j]);

                if (tmp[j] == 0) {
                    hasZero = true;
                    zeroRow = i;
                    zeroCol = j;
                }

                m2[i][j] = val25[0];
                m5[i][j] = val25[1];

                if (i == 0) {
                    if (j != 0) {
                        m2[0][j] += m2[0][j - 1];
                        m5[0][j] += m5[0][j - 1];
                    }
                }
                else {
                    if (j == 0) {
                        m2[i][0] += m2[i - 1][0];
                        m5[i][0] += m5[i - 1][0];

                        p2[i][0] = true;
                        p5[i][0] = true;
                    }
                    else {
                        if (m2[i - 1][j] < m2[i][j - 1]) {
                            m2[i][j] += m2[i - 1][j];
                            p2[i][j] = true;
                        }
                        else
                            m2[i][j] += m2[i][j - 1];

                        if (m5[i - 1][j] < m5[i][j - 1]) {
                            m5[i][j] += m5[i - 1][j];
                            p5[i][j] = true;
                        }
                        else
                            m5[i][j] += m5[i][j - 1];
                    }
                }
            }
        }

        if (m2[n - 1][n - 1] < m5[n - 1][n - 1]) {
            if (hasZero && m2[n - 1][n - 1] > 1) {
                System.out.println(1);
                System.out.println(getPathWithZero(zeroRow, zeroCol, n));
            }
            else {
                System.out.println(m2[n - 1][n - 1]);
                System.out.println(getPath(p2));
            }
        }
        else {
            if (hasZero && m5[n - 1][n - 1] > 1) {
                System.out.println(1);
                System.out.println(getPathWithZero(zeroRow, zeroCol, n));
            }
            else {
                System.out.println(m5[n - 1][n - 1]);
                System.out.println(getPath(p5));
            }
        }
    }

    private static int[] parse(String line, int n) {
        int[] res = new int[n];

        int val = 0, idx = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                res[idx] = val;
                val = 0;
                idx++;
            }
            else
                val = val * 10 + (line.charAt(i) - '0');
        }

        if (idx < n)
            res[idx] = val;

        return res;
    }

    private static String getPathWithZero(int zeroRow, int zeroCol, int n) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < zeroRow; i++)
            res.append('D');

        for (int i = 0; i < n - 1; i++)
            res.append('R');

        for (int i = zeroRow; i < n - 1; i++)
            res.append('D');

        return res.toString();
    }

    private static String getPath(boolean[][] path) {
        StringBuilder res = new StringBuilder();

        int i = path.length - 1, j = path.length - 1;
        while (i > 0 || j > 0) {
            if (path[i][j]) {
                res.append('D');
                i--;
            }
            else {
                res.append('R');
                j--;
            }
        }

        return res.reverse().toString();
    }

    private static int[] get25(int a) {
        int[] res = new int[2];

        if (a == 0)
            return new int[]{1, 1};

        while ((a & 0x1) == 0) {
            res[0]++;
            a = a >> 1;
        }

        while (a % 5 == 0) {
            res[1]++;
            a /= 5;
        }

        return res;
    }
}
*/
