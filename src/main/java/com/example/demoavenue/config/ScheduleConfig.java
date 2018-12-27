package com.example.demoavenue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定时任务相关配置
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    /**
     * 自定义线程池
     * 默认的只有一个线程，不能支持多任务
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    /**
     * 因为线程池是JVM级别的，服务停止了线程池并未停止
     * 所以在Spring Bean结束的时候，调用bean的shutdown方法，结束线程池
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    public ExecutorService taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
