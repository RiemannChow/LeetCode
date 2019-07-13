package com.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author riemann
 * @date 2019/07/10 22:14
 */
public class Person {

    private String name;

    private int age;

    private String sex;

    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public static void main(String[] args) {
        /*List<Person> list = new ArrayList<>();
        list.add(new Person("riemann", 27, "男"));
        list.add(new Person("edgar", 25, "男"));
        list.add(new Person("andy", 30, "女"));
        list.add(new Person("jack", 25, "男"));

        list.stream().filter(person -> person.getSex().equals("男")).forEach(person -> System.out.println(person.toString()));*/

        /*Iterator<Person> iterator = list.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getSex().equals("男")) {
                System.out.println(person.toString());
            }
        }*/

        List<String> list = new ArrayList<>();
        list.add("aaa bbb ccc");
        list.add("ddd eee fff");
        list.add("ggg hhh iii");

        list = list.stream()
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .collect(toList());
        System.out.println(list);
    }

}
