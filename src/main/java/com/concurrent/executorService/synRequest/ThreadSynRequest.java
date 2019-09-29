package com.concurrent.executorService.synRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author riemann
 * @date 2019/09/12 22:45
 */
/*public class ThreadSynRequest {

    //首页返回所需的参数和接口，电影接口
    //热门电影
    private String hotFilmUrl = "http://expand.video.iqiyi.com/api/top/list.json?apiKey=?&topType=1&categoryId=1&limit=30&sr=1";
    //热门电视剧
    private String hotTeleplayUrl = "http://expand.video.iqiyi.com/api/top/list.json?apiKey=?&topType=1&categoryId=2&limit=5&sr=2";
    //热门动漫
    private String hotAnimationUrl = "http://expand.video.iqiyi.com/api/top/list.json?apiKey=?&topType=1&categoryId=4&limit=5&sr=3";

    public LinkedList<JSONObject> threadHandle() throws ExecutionException, InterruptedException {
        //组合线程请求参数
        JSONArray jsonArray = new JSONArray();

        JSONObject o1 = new JSONObject();
        o1.put("requestUrl", "hotFilmUrl");
        o1.put("dataType", "hotFilm");
        o1.put("type", "video");

        JSONObject o2 = new JSONObject();
        o2.put("requestUrl", "hotTeleplayUrl");
        o2.put("dataType", "hotTeleplay");
        o2.put("type", "video");

        JSONObject o3 = new JSONObject();
        o3.put("requestUrl", "hotAnimationUrl");
        o3.put("dataType", "hotAnimation");
        o3.put("type", "video");

        jsonArray.add(o1);
        jsonArray.add(o2);
        jsonArray.add(o3);

        //申明线程池
        ExecutorService exe = Executors.newFixedThreadPool(3);
        //申明数据回调处理类List<Future<JSONObject>>
        List<Future<JSONObject>> futures = new ArrayList<Future<JSONObject>>();

        for (int i = 0; i < 3; i++) {
            JSONObject unitObject = jsonArray.getJSONObject(i);
            //申请单个线程执行类
            VideoSynRequest call = new VideoSynRequest(unitObject);
            //提交单个线程
            Future<JSONObject> future = exe.submit(call);
            //将每个线程放入线程集合， 这里如果任何一个线程的执行结果没有回调，线程都会自动堵塞
            futures.add(future);
        }
        //所有线程执行完毕之后会执行下面的循环，然后通过循环每个个线程后执行线程的get()方法每个线程执行的结果
        for (Future<JSONObject> future : futures) {
            JSONObject json = future.get();
            AAAANewAppShareSingleton.getInstance().homePageSessionDictionary.put(json.getString("dataType"), json.getJSONArray("returnData"));

        }
        AAAANewAppShareSingleton.getInstance().homeIsOrNoReturn = 1;
        //关闭线程池
        exe.shutdown();

        //这里由于我直接将返回结果放入到单利中缓存了，所有返回null
        return null;
    }
}*/


