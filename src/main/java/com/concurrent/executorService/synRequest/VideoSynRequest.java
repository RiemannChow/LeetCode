package com.concurrent.executorService.synRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author riemann
 * @date 2019/09/12 23:26
 */
//public class VideoSynRequest implements Callable<JSONObject> {
//
//    private JSONObject parameter;
//
//    public VideoSynRequest(JSONObject parameter) {
//        this.parameter = parameter;
//        String requestUrl = httpGetRequest(this.parameter.getString("requestUrl"));
//        JSONObject object = new JSONObject(requestUrl);
//        //请求的爱奇艺接口
//        if (this.parameter.get("type").equals("video")) {
//            JSONArray data = object.getJSONArray("data");
//            if (this.parameter.getString("dataType").equals("firstSegmentData")) {
//                JSONArray againArray=new JSONArray();
//                for (int j=0;j<data.length();j++)
//                {
//                    JSONArray tempArrray=new JSONArray();
//                    tempArrray.put(returnArray.getJSONObject(j));
//                    tempArrray.put(returnArray.getJSONObject(j+1));
//
//                    againArray.put(tempArrray);
//                    j=j+1;
//
//                }
//                this.parameter.put("returnData",againArray);
//            }
//        }
//    }
//
//    private String httpGetRequest(String requestUrl) {
//        String result = "";
//        BufferedReader in = null;
//        try {
//            URL url = new URL(requestUrl);
//            // 打开和URL之间的连接
//            URLConnection urlConnection = url.openConnection();
//            // 设置通用的请求属性
//            urlConnection.setRequestProperty("accept", "*/*");
//            urlConnection.setRequestProperty("connection", "Keep-Alive");
//            urlConnection.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            urlConnection.connect();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = urlConnection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {}
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送GET请求出现异常！" + e);
//            e.printStackTrace();
//        } finally { // 使用finally块来关闭输入流
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public JSONObject call() throws Exception {
//        return this.parameter;
//    }
//
//}
