package com.wuhang.xunjian.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvReader;
import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.wuhang.xunjian.service.ReadCsv;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ReadCSVImpl
 * @Description 从文件夹读取csv实现
 * @Author lin
 * @Date 2020/10/22 18:51
 * @Version 1.0
 */
@Service
public class ReadCsvImpl implements ReadCsv {


    /**
     * @param path 文件夹路径
     * <p>
     * 每个系统获取自己的csv文件
     */
    @Override
    public Map<String, Object> readCsvFromPath(String path) throws Exception {
        File file = new File(path);
        Map<String, Object> param = new HashMap<>();
        if (file.isDirectory()) {
            CsvReader csvReader = null;
            //获取当前系统文件夹下的文件
            File[] files = file.listFiles();
            //获取所有文件
            //获取模版，可以做成配置
            for (File systemFile : files) {
                // 数据格式为json
                if (systemFile.getName().endsWith(".json")) {
                    CharSource charSource = Files.asCharSource(new File(""), Charsets.UTF_8);
                    String xunjan = charSource.read();
                    JSONObject xj = JSONObject.parseObject(xunjan);
                }
                //判断文件是否为csv
                if (systemFile.getName().endsWith(".csv")) {
                    InputStream fileInputStream = new FileInputStream(systemFile);
                    csvReader = new CsvReader(fileInputStream, Charset.forName("UTF-8"));
                    //跳过表头
                    csvReader.readHeaders();
                    while (csvReader.readRecord()) {
                        //解析csv
                        System.out.println(csvReader.get(0));
                        System.out.println(csvReader.get(1));
                        System.out.println(csvReader.get(2));
                    }
                }
            }

        }
        return param;
    }


}
