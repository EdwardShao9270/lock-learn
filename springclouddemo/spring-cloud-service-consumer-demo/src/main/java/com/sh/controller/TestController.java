package com.sh.controller;

import com.google.common.collect.Maps;
import com.sh.service.AccountImgService;
import feign.hystrix.HystrixFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 14:42 2018/12/7
 */
@RestController
@RequestMapping("/consumer")
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,Object> test(){
        Map<String,Object> resultMap = Maps.newHashMap();

        AccountImgService accountImgService = HystrixFeign.builder().client(RibbonClients.).

        String userName = "bjshaohang@corp.enetease.2ecom";

        Map<String, Object> result =  accountImgService.get(userName,"hello world");
        resultMap.put("data",result.get("data"));
        return resultMap;
    }
}
