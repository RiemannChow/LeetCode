package com.nowcoder.huawei;

import java.util.*;

public class SimpleErrorLog {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path;
        String fileName;
        String key;
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        while (sc.hasNext()) {
            path = sc.next();
            int id = path.lastIndexOf("\\");
            fileName = id < 0 ? path : path.substring(id + 1);
            int lineNum = sc.nextInt();
            // 统计频率
            key = fileName + " " + lineNum;
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }

        }
        sc.close();
        printSortAndDeduplication(map);
    }

    private static void printSortAndDeduplication(Map<String, Integer> map) {
        // 对记录进行降序
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            // 降序
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue() == 0 ? (o1.getValue() - o2.getValue())
                        : (o2.getValue() - o1.getValue()));
            }
        });

        // 只输出前8条记录
        int count = 0;
        for (Map.Entry<String, Integer> mapping : list) {
            count++;
            if (count <= 8) {
                String[] str = mapping.getKey().split(" ");
                String m = str[0].length() > 16 ? str[0].substring(str[0].length() - 16) : str[0];
                String n = str[1];
                System.out.println(m + " " + n +" " + mapping.getValue());
            } else {
                break;
            }
        }
    }
}
