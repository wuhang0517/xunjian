package com.wuhang.xunjian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wuhang.xunjian.service.WriteXml;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WriteXmlImplTest {

    @Test
    void writeXml() throws Exception{

        String path = "src/main/resources/xunjian.json";
        String s = Files.readString(Path.of(path));
        System.out.println(s);
        Map map = JSONObject.parseObject(s, Map.class);
        WriteXml writeXml = new WriteXmlImpl();
        String xml = writeXml.writeXml(map);
        System.out.println(xml);
    }
}