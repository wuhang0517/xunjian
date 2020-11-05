package com.wuhang.xunjian;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName Test
 * @Description
 * @Author lin
 * @Date 2020/10/27 21:51
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        String ss = "[\"aaa\",\"bbb\"]";
        Test test = new Test();
        JSONArray parse = test.parse(ss, JSONArray.class);
        System.out.println(parse.get(1));
    }

    private <T extends JSON> T parse(String param, Class<T> paramToType) {
        try {
            if (paramToType == JSONObject.class) {
                return (T)JSONObject.parse(param);
            } else if (paramToType == JSONArray.class) {
                return (T)JSONArray.parse(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (paramToType == JSONObject.class) {
                return (T) new JSONObject();
            } else if (paramToType == JSONArray.class) {
                return (T) new JSONArray();
            }
        }
        return null;
    }
}
