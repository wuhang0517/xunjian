package com.wuhang.xunjian.service.impl;

import com.google.common.base.Charsets;
import com.wuhang.xunjian.service.WriteXml;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Map;

/**
 * @ClassName WriteXmlImpl
 * @Description
 * @Author lin
 * @Date 2020/10/30 14:02
 * @Version 1.0
 */
@Component
public class WriteXmlImpl implements WriteXml {

    static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        configuration.setTemplateLoader(new StringTemplateLoader());
        configuration.setDefaultEncoding(Charsets.UTF_8.name());
        configuration.setNumberFormat("computer");

    }

    @Override
    public String writeXml(Map param) throws Exception {
        String temp = "src/main/resources/xunjian.ftl";
        Reader reader = new FileReader(temp);
        StringWriter sw = new StringWriter();
        try {
            Template template = new Template("xunjian", reader, configuration);
            template.process(param, sw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
