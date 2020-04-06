package com.bajins.demo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Description:
 * @Author: https://www.bajins.com
 * @File: ConfigServerApplication.java
 * @Version: 1.0.0
 * @Time: 2020/3/20/020 15:57
 * @Project: spring-cloud-demo
 * @Package: com.bajins.demo
 * @Software: IntelliJ IDEA
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
