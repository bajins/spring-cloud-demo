package com.bajins.demo.computetwo.controller;

import com.bajins.demo.computetwo.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: https://www.bajins.com
 * @File: TestController.java
 * @Version: 1.0.0
 * @Time: 2020/3/23/023 9:59
 * @Project: spring-cloud-demo
 * @Package: com.bajins.demo.controller
 * @Software: IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "compute")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    private List<Map<String, String>> userList;

    @PostConstruct
    public void initData() {
        userList = new ArrayList<>();
        Map<String, String> macro = new HashMap<>();
        macro.put("macro", "123456");
        userList.add(macro);
        Map<String, String> andy = new HashMap<>();
        andy.put("andy", "123456");
        userList.add(andy);
        Map<String, String> mark = new HashMap<>();
        mark.put("mark", "123456");
        userList.add(mark);
    }

    @GetMapping(value = "add")
    public Result add(@RequestParam Integer a, @RequestParam Integer b) {
        log.info("执行add方法：a={},b={}", a, b);
        return new Result(a + b);
    }

    @RequestMapping("list/all")
    public Result listAll() {
        return new Result(userList);
    }

}
