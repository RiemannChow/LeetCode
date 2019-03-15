/*
package com.test;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {

    public static void main(String[] args) {

        boolean flag = false;
        ArrayList<String> list = new ArrayList<String>();
        list.add("andy");
        list.add("edgar");
        list.add("riemann");
        list.add("tom");
        list.add("jack");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if ("riemann".equals(element)) {
                int index = list.indexOf(element);
                list.set(index,"riemannUpdate");
                flag = true;
                break;
            }

        }

        if (flag) {
            System.out.println("修改成功");
            System.out.print(list);
        }else{
            System.out.println("修改失败");
        }

    }

}
*/
