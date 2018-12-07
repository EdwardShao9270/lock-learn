package com.sh.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: bjshaohang
 * @Description:
 * @Date: Created in 16:05 2018/12/7
 */
@Configuration
public class FeignConfiguration {
    //Contract feign的默认契约
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

}