package com.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.regex.Pattern;

/**
 * 正则表达式校验经纬度的合法性
 * @author riemann
 * @date 2020/01/03 21:45
 */
public class VerifyLongAndLat {

    private static final String LONGITUDE = "/^[\\-\\+]?(0?\\d{1,2}\\.\\d{1,5}|1[0-7]?\\d{1}\\.\\d{1,5}|180\\.0{1,5})$/";

    private static final String LATITUDE = "/^[\\-\\+]?(0?\\d{1,2}\\.\\d{1,5}|1[0-7]?\\d{1}\\.\\d{1,5}|180\\.0{1,5})$/";

    /*private JSONArray verifyLongAndLat(List<LocatorDataDetail> ldDetailList, JSONArray errorTypeArray) {
        for (LocatorDataDetail dataDetail : ldDetailList) {
            JSONObject errorTypeObject = new JSONObject();
            Double longitude = dataDetail.getLongitude();
            Double latitude = dataDetail.getLatitude();

            boolean longPattern = Pattern.matches(LONGITUDE, longitude.toString());
            boolean latPattern = Pattern.matches(LATITUDE, latitude.toString());

            if (longPattern || latPattern) {
                errorTypeObject.put("coordinate","0");
            } else {
                errorTypeObject.put("coordinate","2");
            }
            errorTypeArray.add(errorTypeObject);
        }
        return errorTypeArray;
    }*/
}
