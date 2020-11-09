package com.wuhang.xunjian.readdata.impl;

import com.csvreader.CsvReader;
import com.wuhang.xunjian.pojo.TempData;
import com.wuhang.xunjian.readdata.ReadData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @ClassName ReadDataFromCsv
 * @Description 从csv中读取数据
 * @Author lin
 * @Date 2020/11/4 23:39
 * @Version 1.0
 */
public class ReadDataFromCsv implements ReadData {
    @Override
    public TempData readDataFromFile(File file) {
        try {
            InputStream fileInputStream = new FileInputStream(file);
            CsvReader csvReader = new CsvReader(fileInputStream, Charset.forName("UTF-8"));
            //跳过表头
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                //解析csv
                System.out.println(csvReader.get(0));
                System.out.println(csvReader.get(1));
                System.out.println(csvReader.get(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String convertTempData(String data) {
        return null;
    }
}
