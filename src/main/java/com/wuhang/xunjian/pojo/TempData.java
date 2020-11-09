package com.wuhang.xunjian.pojo;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName TempData
 * @Description 模版数据
 * @Author lin
 * @Date 2020/11/5 16:42
 * @Version 1.0
 */
@Data
public class TempData {

    //文件名称
    private String filename;

    //从文件读出来的数据
    private String fileData;

    //转换后的数据
    private Map<String, Map<String,Object>> convertDataRc;

    //转换后的数据
    private Map<String, Map<String,String>> convertDataTt;

    //汇总的数据，映射到模版里
    private Map<String, Object> tempData;

    //总行数
    private int allCount;
}
