package com.leetcode.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
 *
 * 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
 *
 * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
 *
 * 返回 words 中与给定模式匹配的单词列表。
 *
 * 你可以按任何顺序返回答案。
 *
 * 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * 输出：["mee","aqq"]
 * 解释：
 * "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
 * "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
 * 因为 a 和 b 映射到同一个字母。
 */
public class LeetCode890 {
    public static void main(String[] args) {
        String[] words = {"abc","deq","mee","aqq","dkd","ccc"};
        String pattern = "abb";
        System.out.println(findAndReplacePattern(words,pattern));
    }
    public static List<String> findAndReplacePattern(String[] words, String pattern){
        List<String> result = new ArrayList<>();//返回结果
        char[] patternArray = pattern.toCharArray();//字符串转字符数组
        for(int i=0;i<words.length;i++){//遍历每一个words中的字符串
            char [] wordArray = words[i].toCharArray();
            boolean flag = true;//标记，为true说明匹配
            if(wordArray.length == patternArray.length){//匹配肯定长度要相等
                HashMap<Character,Character> judge = new HashMap<>();//hashmap1
                HashMap<Character,Character> judge2 = new HashMap<>();//反向比较hashmap2
                for(int j=0;j<pattern.length();j++){//遍历模式串中的每一个字符
                    if(judge.containsKey(patternArray[j]) == false){
                    //判断key中是否存在这个字符，不存在hashmap1就添加key-value
                        judge.put(patternArray[j],wordArray[j]);
                        if(judge2.containsKey(wordArray[j]) == false){
                        //这个if else的作用就是思路中的hashmap2的作用了
                            judge2.put(wordArray[j],patternArray[j]);//不存在就添加
                        }else {
                            if(judge2.get(wordArray[j]) != patternArray[j]){
                                flag = false;//存在的话就去判断是不是相等的，不相等就不匹配
                                break;
                            }
                        }
                    }else {//存在的话 去hashmap中找这个字符与现有的字符是否相等
                        //不相等，说明存在了（a-c a-b 一个a对应两个字符）直接返回false,不匹配
                        if(judge.get(patternArray[j]) != wordArray[j]){
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag)
                {
                    result.add(words[i]);
                }
            }
        }
        return result;
    }
}
