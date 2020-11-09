package com.wuhang.xunjian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.wuhang.xunjian.pojo.TempData;
import com.wuhang.xunjian.readdata.factory.ReadDataFactory;
import com.wuhang.xunjian.service.ConvertTempData;
import com.wuhang.xunjian.service.WriteXml;
import com.wuhang.xunjian.writedata.ConvertData;
import com.wuhang.xunjian.writedata.impl.ConvertDataRC;
import com.wuhang.xunjian.writedata.impl.ConvertDataTT;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ConvertTempData
 * @Description 合并数据
 * @Author lin
 * @Date 2020/11/5 23:39
 * @Version 1.0
 * <p>
 * //todo   1. 修改模版格式。
 * 2. 修改成为定时任务的方式。
 * 3. 修改成为多线程的方式。
 * <p>
 * 邮件反馈 json缺失内容，不正确内容。生成模版，代码
 */
@Component
public class ConvertTempDataImpl implements ConvertTempData {

    @Override
    public Map<String, Object> convertData(String path) throws Exception {
        File everyFile = new File(path);
        if (everyFile.isDirectory()) {
            File[] files = everyFile.listFiles();
            TempData tt = null;
            TempData rc = null;
            for (File file : files) {
                if (file.getName().contains("RC")) {
                    rc = ReadDataFactory.build().read(file);
                }
                if (file.getName().contains("TT")) {
                    tt = ReadDataFactory.build().read(file);
                }
            }
            ConvertData convertDataTt = new ConvertDataTT();
            TempData ttData = convertDataTt.convertData(tt);
            ConvertData convertDataRC = new ConvertDataRC();
            TempData rcData = convertDataRC.convertData(rc);
            Map<String, Map<String, String>> convertTt = ttData.getConvertDataTt();
            Map<String, Map<String, Object>> convertRc = rcData.getConvertDataRc();
            List list = new ArrayList();
            convertTt.forEach((key, value) -> {
                Map<String, Object> result = new HashMap<>();
                Map<String, Object> value2 = convertRc.get(key);
                result.putAll(value);
                if (value2 != null) {
                    result.putAll(value2);
                }
                list.add(result);
            });
            Map<String, Object> map = new HashMap<>();
            map.put("allCount", rcData.getAllCount() + convertTt.size() + 1);
            map.put("record", list);
            return map;
        }
        return null;
    }

    public static void main1(String[] args) throws Exception {
        TempData tt = ReadDataFactory.build().read(new File("src/main/resources/XJ-EIMP-TT-2020-11-04-00-2020-11-04-10.json"));
        TempData rc = ReadDataFactory.build().read(new File("src/main/resources/XJ-EIMP-RC-2020-11-04-00-2020-11-04-10.json"));
        ConvertData convertDataTt = new ConvertDataTT();
        TempData ttData = convertDataTt.convertData(tt);
        ConvertData convertDataRC = new ConvertDataRC();
        TempData rcData = convertDataRC.convertData(rc);
        Map<String, Map<String, String>> convertTt = ttData.getConvertDataTt();
        Map<String, Map<String, Object>> convertRc = rcData.getConvertDataRc();
        List list = new ArrayList();
        convertTt.forEach((key, value) -> {
            Map<String, Object> result = new HashMap<>();
            Map<String, Object> value2 = convertRc.get(key);
            result.putAll(value);
            if (value2 != null) {
                result.putAll(value2);
            }
            list.add(result);
        });
        Map<String, Object> map = new HashMap<>();
        map.put("allCount", rcData.getAllCount() + convertTt.size() + 1);
        map.put("record", list);
        WriteXml writeXml = new WriteXmlImpl();
        String s = writeXml.writeXml(map);
        CharSink charSink = Files.asCharSink(new File("src/main/resources/xunjian.xml"), Charsets.UTF_8);
        charSink.write(s);
    }

    public static void main(String[] args) throws Exception {
        CharSource charSource = Files.asCharSource(new File("src/main/resources/xunjian.json"), Charsets.UTF_8);
        String xunjan = charSource.read();
        Map map = JSONObject.parseObject(xunjan, Map.class);
        WriteXml writeXml = new WriteXmlImpl();
        String s = writeXml.writeXml(map);
        System.out.println(s);

    }
}
