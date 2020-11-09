package com.wuhang.xunjian.readdata;

import com.wuhang.xunjian.pojo.TempData;

import java.io.File;

/**
 * @ClassName ReadDataFactory
 * @Description 读取数据的接口
 * @Author lin
 * @Date 2020/11/4 23:36
 * @Version 1.0
 */
public interface ReadData {

    /**
     * 从文本中读取数据，并解析成为固定的格式
     *
     * @param file
     * @return
     */
    TempData readDataFromFile(File file);

    /**
     * 将从文件读到的数据转换为模版需要的数据
     *
     * @param data
     * @return
     */
    String convertTempData(String data);
}
