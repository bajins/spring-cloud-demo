package com.bajins.demo.compute.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping(value = "config")
public class TestConfigController {
    private static final Logger log = LoggerFactory.getLogger(TestConfigController.class);

    @Value("${from}")
    private String from;


    @GetMapping(value = "from")
    public String testConfigClient() {
        log.info("测试配置：{}", from);
        return from;
    }
}
