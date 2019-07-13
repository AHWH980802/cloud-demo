package cn.itcast.controller;

import cn.itcast.client.UserClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: 萧一旬
 * @Description:
 * @Date: Create in 14:02 2019/4/10
 */
@RequestMapping("consumer")
@Controller
@Slf4j
@DefaultProperties(defaultFallback = "helloFallbackMethod")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    UserClient userClient;

    @GetMapping("{id}")
    @ResponseBody
    @HystrixCommand
    public String hello(@PathVariable("id") int id) {
        return userClient.query(id);
    }

    /*@GetMapping("{id}")
    @ResponseBody
    *//*@HystrixCommand(fallbackMethod = "helloFallbackMethod1",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*//*
    *//*@HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })*//*
    public String hello(@PathVariable int id) {
        *//*if (id % 2 == 0) {
            throw new RuntimeException("");
        }*//*
        String url = "http://user-service/user/" + id;
        String str = restTemplate.getForObject(url, String.class);
        return str;
    }*/

    public String helloFallbackMethod1(int id) {
        return "服务器炸了11";
    }

    public String helloFallbackMethod() {
        return "服务器炸了";
    }

    /*@GetMapping("{id}")
    @ResponseBody
    public String hello(@PathVariable int id) {
        //根据服务id去获取实例
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        //从实例中取出ip和端口
//        ServiceInstance instance = instances.get(0);

        //String url = "http://127.0.0.1:8888/user/"+id;
        //String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
        String url = "http://user-service/user/" + id;
        log.debug(url);
        String str = restTemplate.getForObject(url, String.class);
        return str;
    }*/
}
