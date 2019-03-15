package com.swordMeansOffer;

/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */

public class Demo1 {
    public static void main(String[] args) {
        int arr[][] = {{1,2,8,9}, {2,4,9,12},{4,7,10,13},{6,8,11,15}};
        System.out.println(Find(6,arr));
    }
    public static boolean Find(int target, int[][] array){
        int row_len = array.length;
        int col_len = array[0].length;
        int row;
        int col;
        for(row = 0, col = col_len - 1; col >= 0 && row < row_len;){
            if(target == array[row][col]){
                return true;
            }else if(array[row][col] > target){
                col--;
                continue;
            }else{
                row++;
                continue;
            }
        }
        return false;
    }
}
