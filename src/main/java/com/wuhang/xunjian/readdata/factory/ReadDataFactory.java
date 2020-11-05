package com.wuhang.xunjian.readdata.factory;

import com.wuhang.xunjian.readdata.ReadData;
import com.wuhang.xunjian.readdata.impl.ReadDataFromCsv;
import com.wuhang.xunjian.readdata.impl.ReadDataFromJson;

import java.io.File;
import java.util.Map;

/**
 * @ClassName ReadDataFactory
 * @Description 读取数据的工厂
 * @Author lin
 * @Date 2020/11/4 23:40
 * @Version 1.0
 */
public class ReadDataFactory {

    Map<String, ReadData> readDataMap;

    private ReadDataFactory() {
        ReadData readDataFromCsv = new ReadDataFromCsv();
        ReadData readDataFromJson = new ReadDataFromJson();
        readDataMap.put("json", readDataFromJson);
        readDataMap.put("csv", readDataFromCsv);
    }

    /**
     * 构建一个factory
     *
     * @return
     */
    public static ReadDataFactory build() {
        return new ReadDataFactory();
    }

    /**
     * 读取文件
     *
     * @param file
     * @return
     */
    public String read(File file) {

        String[] names = file.getName().split(".");
        String suffix = names[names.length - 1];
        ReadData readData = readDataMap.get(suffix);
        String data = readData.readDataFromFile(file);
        return readData.convertTemp(data);
    }


}
