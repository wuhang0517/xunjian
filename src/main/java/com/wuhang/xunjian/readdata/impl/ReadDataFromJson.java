package com.wuhang.xunjian.readdata.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.wuhang.xunjian.pojo.TempData;
import com.wuhang.xunjian.readdata.ReadData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
    public TempData readDataFromFile(File file) {
        try {
            TempData tempData = new TempData();
            CharSource charSource = Files.asCharSource(file, Charsets.UTF_8);
            String xunjan = charSource.read();
//            tempData.setFilename(file.getName());
            tempData.setFileData(xunjan);

            return tempData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String convertTempData(String data) {
        JSONObject xj = JSONObject.parseObject(data);
        JSONObject hits = xj.getJSONObject("hits");
        //交易总量
        String allTotal = hits.getString("total");
        JSONObject aggregations = xj.getJSONObject("aggregations");
        JSONObject distributionResult = aggregations.getJSONObject("distribution_result");
        JSONArray buckets = distributionResult.getJSONArray("buckets");
        //模版数据的map
        Map<String, Object> temp = new HashMap<>();
        //每个交易码对应的map
        Map<String, Object> record = new HashMap<>();
        for (Object bucket : buckets) {
            //合并单元格的内容
            Map<String, Object> bucketMap = new HashMap<>();
            JSONObject xjBucket = JSONObject.parseObject(bucket.toString());
            //交易码
            String trxCode = xjBucket.getString("key");
            //总交易量
            String tranCount = xjBucket.getString("doc_count");
            //承兑率
            String acceptRet = JSONObject.parseObject(xjBucket.getString("_busi_success_count")).getString("doc_count");
            //平均响应时间
            String ansTime = JSONObject.parseObject(xjBucket.getString("_latency_msec_avg")).getString("doc_count");
            //响应率
            String ansRet = JSONObject.parseObject(xjBucket.getString("_response_count")).getString("doc_count");
            //成功率
            String sucRet = JSONObject.parseObject(xjBucket.getString("_success_count")).getString("doc_count");
            bucketMap.put("trxCode", trxCode);
            bucketMap.put("tranCount", tranCount);
            bucketMap.put("acceptRet", acceptRet);
            bucketMap.put("ansTime", ansTime);
            bucketMap.put("ansRet", ansRet);
            bucketMap.put("sucRet", sucRet);
            record.put(trxCode, bucketMap);
        }

        return null;
    }
}
