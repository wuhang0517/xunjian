package com.wuhang.xunjian.service.impl;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.Files;
import com.wuhang.xunjian.service.ConvertTempData;
import com.wuhang.xunjian.service.GeneraterReport;
import com.wuhang.xunjian.service.WriteXml;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName GenerateReport
 * @Description 生成巡检报告
 * @Author lin
 * @Date 2020/11/9 23:31
 * @Version 1.0
 */
@Service
@EnableScheduling
public class GenerateReportImpl implements GeneraterReport {

    @Value("${report.address}")
    private String address;

    @Resource
    ConvertTempData convertTempData;

    @Resource
    WriteXml writeXml;

    @Override
    public void generateReport(String path) {
        try {
            Map<String, Object> tempData = convertTempData.convertData(path);
            String report = writeXml.writeXml(tempData);
            CharSink charSink = Files.asCharSink(new File(path), Charsets.UTF_8);
            charSink.write(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "* * 15 * * ?")
    public void autoGenerate() throws Exception {
        File file = new File(address);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
            for (File every : files) {
                GenerateCheckReport generateCheckReport = new GenerateCheckReport(every.getPath());
                fixedThreadPool.execute(generateCheckReport);
            }
        }
    }

    private class GenerateCheckReport implements Runnable {

        private String path;

        private GeneraterReport generaterReport;

        public GenerateCheckReport(String path) {
            this.path = path;
            this.generaterReport = new GenerateReportImpl();
        }

        @Override
        public void run() {
            generaterReport.generateReport(path);
        }
    }


}
