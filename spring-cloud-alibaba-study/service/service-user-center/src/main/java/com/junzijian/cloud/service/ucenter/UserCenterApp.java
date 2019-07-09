package com.junzijian.cloud.service.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liuzhe
 * @date 2019/4/29
 */
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.junzijian.framework.model") //扫描实体类
@ComponentScan("com.junzijian.framework.common")
@ComponentScan("com.junzijian.cloud.service.ucenter")
public class UserCenterApp {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApp.class, args);
    }
}