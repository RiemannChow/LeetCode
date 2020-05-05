package com.test.javaPerformanceOptimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCollection {

    private final List<String> str = new ArrayList<>();

    private static final String[] EMPTY_BOOK_ARRAY = new String[0];

    public String[] getStr() {
        return str.toArray(EMPTY_BOOK_ARRAY);
    }

    public List<String> getList() {
        if (str.isEmpty()) {
            return Collections.emptyList();
        } else {
            return new ArrayList<String>(str);
        }
    }

    public static void main(String[] args) {
        TestCollection test = new TestCollection();
        System.out.println(test.getStr());

        String[] arr = {"3","2","6"};
        System.out.println(Arrays.asList(arr).contains("2"));
        List<String> list = new ArrayList<>();
        list.add("3");
        list.add("2");
        list.add("5");
        System.out.println(list.toArray().toString());
    }

}
