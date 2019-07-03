package com.test;

//import com.alibaba.fastjson.JSONObject;
import com.test.objectMapper.RiemannUser;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author riemann
 * @date 2019/07/03 23:04
 */
public class BasicQuestionTest {

    public static void main(String[] args) {
        RiemannUser riemannUser = new RiemannUser();
        riemannUser.setId(1);
        riemannUser.setMessage("Hello JSONObject");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        riemannUser.setSendDate(date);
        // 1、alibaba的JSONObject对象调用toJSONString方法直接转换
        //String jsonString = JSONObject.toJSONString(riemannUser);

        // 2、net.sf.json.JSONObject 的实现
        JSONObject jsonObject = JSONObject.fromObject(riemannUser);
        String jsonString = jsonObject.toString();
        System.out.println(jsonString);
    }

}
