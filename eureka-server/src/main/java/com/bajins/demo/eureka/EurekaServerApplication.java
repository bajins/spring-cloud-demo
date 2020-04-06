package com.bajins.demo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description:
 * @Author: https://www.bajins.com
 * @File: EurekaServerApplication.java
 * @Version: 1.0.0
 * @Time: 2020/3/20/020 10:40
 * @Project: spring-cloud-demo
 * @Package: com.bajins.demo
 * @Software: IntelliJ IDEA
 */
// 启动一个服务注册中心提供给其他应用进行对话
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
