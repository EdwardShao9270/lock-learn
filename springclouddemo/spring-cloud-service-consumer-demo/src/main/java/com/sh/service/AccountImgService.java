package com.sh.service;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 14:43 2018/12/7
 */
@FeignClient(name = "faAccountData")
public interface AccountImgService {

    /**
     * @param userName
     * @param demo
     * @return
     */
    @RequestLine("GET /account/img/get/{userName}?demo={demo}")
    Map<String,Object> get(@Param("userName")String  userName, @Param("demo") String demo);
}
