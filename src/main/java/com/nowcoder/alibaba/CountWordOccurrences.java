package com.nowcoder.alibaba;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CountWordOccurrences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new LinkedHashMap<>();
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] splitArr = s.trim().split("[，。？,.? ]");
            for (int i = 0; i < splitArr.length; i++) {
                if (!("".equals(splitArr[i]))) {
                    Iterator<String> it = map.keySet().iterator();
                    boolean exist = false;
                    while (it.hasNext()) {
                        String key = it.next();
                        if (key.equalsIgnoreCase(splitArr[i])) {
                            exist = true;
                            map.put(key, map.get(key) + 1);
                        }
                    }
                    if (exist == false) {
                        map.put(splitArr[i], 1);
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
