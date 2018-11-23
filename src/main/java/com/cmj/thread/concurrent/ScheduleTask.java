package com.cmj.thread.concurrent;

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
public class ScheduleTask {

    @Resource(name = "scheduledExecutorService")
    private ScheduledExecutorService scheduledExecutorService;

    //    Cron表达式由6~7项组成，中间用空格分开。从左到右依次是：秒、分、时、日、月、周几、年（可省略）。值可以是数字，也可以是以下符号：
//            *：所有值都匹配
//?：无所谓，不关心，通常放在“周几”里
//,：或者
///：增量值
//-：区间
//
//    下面举几个例子，看了就知道了：
//            0 * * * * *：每分钟（当秒为0的时候）
//            0 0 * * * *：每小时（当秒和分都为0的时候）
//            */10 * * * * *：每10秒
//0 5/15 * * * *：每小时的5分、20分、35分、50分
//0 0 9,13 * * *：每天的9点和13点
//0 0 8-10 * * *：每天的8点、9点、10点
//0 0/30 8-10 * * *：每天的8点、8点半、9点、9点半、10点
//0 0 9-17 * * MON-FRI：每周一到周五的9点、10点…直到17点（含）
//            0 0 0 25 12 ?：每年12约25日圣诞节的0点0分0秒（午夜）
//            0 30 10 * * ? 2016：2016年每天的10点半
//
//    其中的?在用法上其实和*是相同的。但是*语义上表示全匹配，而?并不代表全匹配，而是不关心。比如对于0 0 0 5 8 ? 2016来说，2016年8月5日是周五，?表示我不关心它是周几。而0 0 0 5 8 * 2016中的*表示周一也行，周二也行……语义上和2016年8月5日冲突了，你说谁优先生效呢。
//
//    不记得也没关系，记住Cron Maker也可以，它可以在线生成cron表达式。
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
