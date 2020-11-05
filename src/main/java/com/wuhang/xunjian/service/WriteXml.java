package com.wuhang.xunjian.service;

import java.util.Map;

/**
 * @ClassName WriteXml
 * @Description 生成巡检报告
 * @Author lin
 * @Date 2020/10/30 13:59
 * @Version 1.0
 */
public interface WriteXml {

    /**
     * @param param
     * @return
     */
    String writeXml(Map<String, Object> param) throws Exception;
}
