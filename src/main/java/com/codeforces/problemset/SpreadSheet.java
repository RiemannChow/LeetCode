package com.codeforces.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpreadSheet {
    static char[] digits = {'a', 'b',
                            'c', 'd', 'e', 'f', 'g', 'h',
                            'i', 'j', 'k', 'l', 'm', 'n',
                            'o', 'p', 'q', 'r', 's', 't',
                            'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        String input;
        while (n-- > 0) {
            input = br.readLine();
            if (input.charAt(0) == 'R' && input.indexOf('C') >= 0 && Character.isDigit(input.charAt(input.indexOf('C') - 1))) {
                int cIndex = input.indexOf('C');
                int row = Integer.parseInt(input.substring(1, cIndex));
                int col = Integer.parseInt(input.substring(cIndex + 1));

                result.append(getRadix(col)).append(row);
            } else {
                int rowIndex = 0;
                while (!Character.isDigit(input.charAt(rowIndex))) {
                    rowIndex++;
                }

                String colStr = input.substring(0, rowIndex);
                int row = Integer.parseInt(input.substring(rowIndex));
                int col = 0;
                while (colStr.length() > 0) {
                    col = col * 26;
                    col = col + colStr.charAt(0) - 'A' + 1;
                    colStr = colStr.substring(1);
                }
                result.append("R").append(row).append("C").append(col);
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }

    private static String getRadix(int i) {
        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            sb.insert(0, digits[(i - 1) % 26]);
            i = (i - 1) / 26;
        }
        return sb.toString().toUpperCase();
    }
}
