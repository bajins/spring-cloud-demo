package com.bajins.demo.compute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: https://www.bajins.com
 * @File: ComputeServiceApplication.java
 * @Version: 1.0.0
 * @Time: 2020/3/20/020 10:49
 * @Project: spring-cloud-demo
 * @Package: com.bajins.demo
 * @Software: IntelliJ IDEA
 */
// 开启Hystrix的断路器功能
@EnableCircuitBreaker
// 从Spring Cloud Edgware开始，@EnableDiscoveryClient 或@EnableEurekaClient 可省略。
// 只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上
// @EnableEurekaClient 只支持Eureka注册中心，@EnableDiscoveryClient 支持Eureka、Zookeeper、Consul 这三个注册中心。
//@EnableEurekaClient
// 在日志中打印出服务实例的相关内容，该注解能激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出
@EnableDiscoveryClient
@SpringBootApplication
//@SpringCloudApplication
public class ComputeServiceApplication {

    // RestTemplate是一个HTTP客户端，使用它我们可以方便的调用HTTP接口
    // 当我们使用RestTemplate来调用其他服务时，Ribbon可以很方便的实现负载均衡功能。
    @Bean
    // 使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ComputeServiceApplication.class, args);
    }
}
