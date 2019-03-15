package com.leetcode.code;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 输入: "race a car"
 * 输出: false
 */
public class LeetCode125 {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }
    public static boolean isPalindrome(String s){
        if(s==null||s.length()<2){
            return true;
        }
        int p2=s.length()-1;
        char c1,c2;
        for(int i=0;i<s.length()-1;i++){
            if(!isvalid(s.charAt(i))){
                continue;
            }
            c1=s.charAt(i);
            while(p2>=0&&!isvalid(s.charAt(p2))){
                p2--;
            }
            c2=s.charAt(p2);
            if(!issame(c1,c2)) return false;
            else p2--;
        }
        return true;
    }
    private static boolean isvalid(char c){
        if(c>='a'&&c<='z'||c>='A'&&c<='Z'||c>='0'&&c<='9')
            return true;
        else
            return false;
    }
    private static boolean issame(char a,char b){
        if(a<65||b<65){
            if(a==b) return true;
            else	return false;
        }else{
            if(a==b||Math.abs(a-b)==32)	return true;
            else return false;
        }
    }


    /*public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }
    public static boolean isPalindrome(String s){
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        String temp = "";
        for (int i = 0; i < charArray.length; i++){
            if ((int)charArray[i] >= 48 && (int)charArray[i] <= 57 || (int)charArray[i] >= 97){
                temp += charArray[i];
            }
        }
        char[] reosultArray = temp.toCharArray();
        int begin = 0;
        int end = reosultArray.length - 1;
        while (begin < end){
            if (reosultArray[begin] == reosultArray[end]){
                begin++;
                end--;
            }else {
                return false;
            }
        }
        return true;
    }*/
}
