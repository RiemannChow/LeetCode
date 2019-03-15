package com.swordMeansOffer;

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Demo2 {
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("Happy New Year");
        System.out.println(replaceSpace(str));
    }
    public static String replaceSpace(StringBuffer str){
        String str1 = str.toString();
        if(str1.equals("")){
            return str1;
        }
        char[] strArray = str1.toCharArray();
        int i = 0;
        int lengthSpace = 0;
        while (i < strArray.length){
            if (strArray[i] == ' '){
                lengthSpace++;
            }
            i++;
        }
        int newStrLength = strArray.length + lengthSpace * 2;
        char[] newStr = new char[newStrLength];
        int j = newStrLength - 1;
        i = strArray.length - 1;
        while (i >= 0){
            if (strArray[i] != ' '){
                newStr[j--] = strArray[i--];
            }else{
                newStr[j--] = '0';
                newStr[j--] = '2';
                newStr[j--] = '%';
                i--;
            }
        }
        return new String(newStr);
    }
}
