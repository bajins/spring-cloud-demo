package com.bajins.demo.compute.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RequestMapping(value = "ribbon")
@RestController
public class TestRibbonController {

    private static final Logger log = LoggerFactory.getLogger(TestRibbonController.class);

    // 注入RestTemplate，使用其调用
    @Autowired
    private RestTemplate restTemplate;

    // 此配置值从配置中心拉取，方便统一管理
    @Value("${service-url.compute-two-service}")
    private String computeTwoService;

    // 此配置值从配置中心拉取，方便统一管理
    @Value("${service-url.compute-three-service}")
    private String computeThreeService;

    @GetMapping(value = "add")
    public String add() {
        return restTemplate.getForEntity(computeTwoService + "/compute/add?a=10&b=20", String.class).getBody();
    }
}
