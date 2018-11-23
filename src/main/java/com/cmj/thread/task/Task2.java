package com.cmj.thread.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cmj
 * @Description:
 * @Date: 2018/11/23
 */
@Component
@Slf4j
public class Task2 {

    @Resource(name = "scheduledExecutorService")
    private ScheduledExecutorService scheduledExecutorService;

    @Scheduled(fixedRate = 1)
    public void test() {
        log.info("1");
    }

    /**
     * 每隔15分钟构建模拟一次用户请求
     */
    @Bean
    public void refresh() {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("2");
                } catch (Exception e) {
                    log.info("任务执行失败，e:{}" + e.getMessage());
                }
            }

        }, 0, 1, TimeUnit.SECONDS);
    }
}
