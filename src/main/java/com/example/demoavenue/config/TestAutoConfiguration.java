package com.example.demoavenue.config;

import com.alibaba.fastjson.JSON;

//@Configuration
//@EnableConfigurationProperties(TestProperties.class)
public class TestAutoConfiguration {

//    @Bean
    public TestProperties test(TestProperties testProperties){

        System.out.println(JSON.toJSONString(testProperties));

        return null;
    }
}
