package com.bajins.demo.compute.controller;


import com.bajins.demo.compute.domain.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping(value = "hystrix")
@RestController
public class TestHystrixController {

    private static final Logger log = LoggerFactory.getLogger(TestHystrixController.class);

    // 注入RestTemplate，使用其调用
    @Autowired
    private RestTemplate restTemplate;

    // 此配置值从配置中心拉取，方便统一管理
    @Value("${service-url.compute-two-service}")
    private String computeTwoService;

    // 如果方法有参数，那么fallbackMethod指定的默认方法也必须有相同参数
    @HystrixCommand(fallbackMethod = "getDefault")
    @GetMapping(value = "testFallback")
    public Result testFallback() {
        return restTemplate.getForObject(computeTwoService + "/compute/add?a={1}&b={2}", Result.class, 10, 20);
    }

    // ignoreExceptions忽略某些异常降级
    @HystrixCommand(fallbackMethod = "getDefault", ignoreExceptions = {NullPointerException.class})
    @GetMapping(value = "testException/{id}")
    public Result testException(@PathVariable Long id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            // 会忽略此异常，服务将不降级
            throw new NullPointerException();
        }
        return restTemplate.getForObject(computeTwoService + "/compute/add?a={1}&b={2}", Result.class, 10, 20);
    }

    // 使用缓存
    // 需要在每次使用缓存的请求前后对HystrixRequestContext进行初始化和关闭，
    // 见：com/bajins/demo/compute/domain/HystrixRequestContextFilter.java
    @GetMapping(value = "testCache/{id}")
    public Result testCache(@PathVariable Long id) {
        // 这里调用多次，如果日志只打印一次说明走的缓存
        getCache(id);
        getCache(id);
        getCache(id);
        return getCache(id);
    }

    // 移除缓存
    @GetMapping("/testRemoveCache/{id}")
    public Result testRemoveCache(@PathVariable Long id) {
        getCache(id);
        getCache(id);
        removeCache(id);
        getCache(id);
        getCache(id);
        return getCache(id);
    }

    // 开启缓存，默认所有参数作为缓存的key，cacheKeyMethod可以通过返回String类型的方法指定key；
    // @CacheKey：指定缓存的key，可以指定参数或指定参数中的属性值为缓存key，cacheKeyMethod还可以通过返回String类型的方法指定；
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefault", commandKey = "getCache")
    public Result getCache(Long id) {
        log.info("getCache id:{}", id);
        return restTemplate.getForObject(computeTwoService + "/compute/add?a={1}&b={2}", Result.class, 10, 20);
    }

    // 移除缓存
    @CacheRemove(commandKey = "getUserCache", cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public Result removeCache(Long id) {
        log.info("removeCache id:{}", id);
        return restTemplate.getForObject(computeTwoService + "/compute/add?a={1}&b={2}", Result.class, 10, 20);
    }


    public Result getDefault() {
        return new Result<>(500, "系统内部错误！");
    }

    public Result getDefault(Long id) {
        return new Result<>(500, "系统内部错误！");
    }

    /**
     * 为缓存生成key
     *
     * @param id
     * @return
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }
}
