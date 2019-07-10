package com.stream;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author riemann
 * @date 2019/07/10 22:14
 */
public class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("riemann", 27));
        list.add(new Person("edgar", 25));
        list.add(new Person("andy", 30));

        list = list.stream().filter(person -> person.getAge() == 27).collect(toList());
        System.out.println(list.toString());
    }

}
