package com.test.objectMapper;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapBeanConvert {

    // JavaBean转换为Map
    public static Map<String, Object> bean2Map(Object bean) throws Exception {
        Map<String, Object> map = new HashMap<>();
        // 获取指定类（User）的BeanInfo对象
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        // 获取所有的属性描述器
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String key = pd.getName();
            Method getter = pd.getReadMethod();
            Object value = getter.invoke(bean);
            map.put(key, value);
        }
        return map;
    }

    // Map转换成JavaBean
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) throws Exception {
        // 创建JavaBean对象
        T obj = clazz.newInstance();
        // 获取指定类的BeanInfo对象
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        // 获取所有的属性描述器
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            Object value = map.get(pd.getName());
            Method setter = pd.getWriteMethod();
            if (value != "") {
                setter.invoke(obj, value);
            }
        }
        return obj;
    }

    static class User {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public User() {
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test
    public void testBean2Map() throws Exception {
        User user = new User("riemann", 28);
        Map<String, Object> map = new HashMap<>();
        map = bean2Map(user);
        System.out.println(map);
    }

    @Test
    public void testMap2Bean() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "riemann");
        map.put("age", 28);
        User user = map2Bean(map, User.class);
        System.out.println(user);
    }

}
