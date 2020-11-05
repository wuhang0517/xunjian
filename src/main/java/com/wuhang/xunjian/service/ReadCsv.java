package com.wuhang.xunjian.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @ClassName ReadCSV
 * @Description 从文件夹中读取csv
 * @Author lin
 * @Date 2020/10/22 18:45
 * @Version 1.0
 */
public interface ReadCsv {

    /**
     * 读取数据文件
     *
     * @param path 文件夹路径
     */
    Map<String, Object> readCsvFromPath(String path) throws Exception;
}
