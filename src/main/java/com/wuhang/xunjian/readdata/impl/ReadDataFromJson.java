package com.wuhang.xunjian.readdata.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.wuhang.xunjian.readdata.ReadData;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ReadDataFromJson
 * @Description 从json中读取数据
 * @Author lin
 * @Date 2020/11/4 23:40
 * @Version 1.0
 */
public class ReadDataFromJson implements ReadData {
    @Override
    public String readDataFromFile(File file) {
        try {
            CharSource charSource = Files.asCharSource(new File(""), Charsets.UTF_8);
            String xunjan = charSource.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String convertTemp(String data) {
        JSONObject xj = JSONObject.parseObject(data);
        JSONObject hits = xj.getJSONObject("hits");
        //交易总量
        String allTotal = hits.getString("total");
        JSONObject aggregations = xj.getJSONObject("aggregations");
        JSONObject distributionResult = aggregations.getJSONObject("distribution_result");
        JSONArray buckets = distributionResult.getJSONArray("buckets");
        Map<String, Object> temp = new HashMap<>();
        List<Map<String, Object>> record = new ArrayList<>();
        for (Object bucket : buckets) {
            Map<String, Object> bucketMap = new HashMap<>();
            JSONObject xjBucket = JSONObject.parseObject(bucket.toString());
            //交易码
            String key = xjBucket.getString("key");
            //总交易量
            String total = xjBucket.getString("doc_count");
            //承兑率
            String busiSuccessCount = JSONObject.parseObject(xjBucket.getString("_busi_success_count")).getString("doc_count");
            //平均响应时间
            String latencyMsecAvg = JSONObject.parseObject(xjBucket.getString("_latency_msec_avg")).getString("doc_count");
            //响应率
            String responseCount = JSONObject.parseObject(xjBucket.getString("_response_count")).getString("doc_count");
            //成功率
            String successCount = JSONObject.parseObject(xjBucket.getString("_success_count")).getString("doc_count");

        }
        return null;
    }
}
