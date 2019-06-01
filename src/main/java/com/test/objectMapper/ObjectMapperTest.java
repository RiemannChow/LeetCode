package com.test.objectMapper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author riemann
 * @date 2019/05/27 22:55
 */
public class ObjectMapperTest {

    public static ObjectMapper mapper = new ObjectMapper();

    static {
        // 转换为格式化的json
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void testObject() throws JsonGenerationException, JsonMappingException, IOException {
        RiemannUser riemann = new RiemannUser(1,"Hello World", new Date());
        mapper.writeValue(new File("D:/test.txt"), riemann);//写到文件中
        //mapper.writeValue(System.out, riemann); //写到控制台

        String jsonStr = mapper.writeValueAsString(riemann);
        System.out.println("对象转json字符串: " + jsonStr);

        byte[] byteArr = mapper.writeValueAsBytes(riemann);
        System.out.println("对象转为byte数组：" + byteArr);

        RiemannUser riemannUser = mapper.readValue(jsonStr, RiemannUser.class);
        System.out.println("json字符串转为对象：" + riemannUser);

        RiemannUser riemannUser2 = mapper.readValue(byteArr, RiemannUser.class);
        System.out.println("byte数组转为对象：" + riemannUser2);

    }

    @Test
    public void testList() throws JsonGenerationException, JsonMappingException, IOException {
        List<RiemannUser> riemannList = new ArrayList<>();
        riemannList.add(new RiemannUser(1,"a",new Date()));
        riemannList.add(new RiemannUser(2,"b",new Date()));
        riemannList.add(new RiemannUser(3,"c",new Date()));

        String jsonStr = mapper.writeValueAsString(riemannList);
        System.out.println("集合转为字符串：" + jsonStr);

        List<RiemannUser> riemannLists = mapper.readValue(jsonStr, List.class);
        System.out.println("字符串转集合：" + riemannLists);

    }

    @Test
    public void testMap() {
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("name", "riemann");
        testMap.put("age", 27);
        testMap.put("date", new Date());
        testMap.put("user", new RiemannUser(1, "Hello World", new Date()));

        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(testMap);
            System.out.println("Map转为字符串：" + jsonStr);
            Map<String, Object> testMapDes = null;
            try {
                testMapDes = mapper.readValue(jsonStr, Map.class);
                System.out.println("字符串转Map：" + testMapDes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOther() throws IOException {
        // 修改时间格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        RiemannUser riemannUser = new RiemannUser(1,"Hello World",new Date());
        riemannUser.setIntList(Arrays.asList(1,2,3));
        String jsonStr = mapper.writeValueAsString(riemannUser);
        System.out.println("对象转为字符串：" + jsonStr);
    }
}
