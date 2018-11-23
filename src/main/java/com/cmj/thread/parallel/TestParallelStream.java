package com.cmj.thread.parallel;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 并行不一定快，
 * 线程上下文切花耗时
 *
 */
public class TestParallelStream {

    @Test
    public void testStream() {
        // 起始时间
        LocalTime start = LocalTime.now();

        List<Integer> list = new ArrayList<>();
        // 将10000-1存入list中
        for (int i = 10000; i >= 1; i--) {
            list.add(i);
        }

        list.stream()// 获取串行流
                .sorted()// 按自然排序，即按数字从小到大排序
                .count();// count()是终止操作，有终止操作才会执行中间操作sorted()

        // 终止时间
        LocalTime end = LocalTime.now();

        // 时间间隔
        Duration duration = Duration.between(start, end);
        // 输出时间间隔毫秒值
        System.out.println(duration.toMillis());
    }

    @Test
    public void testParallelStream() {
        // 起始时间
        LocalTime start = LocalTime.now();

        List<Integer> list = new ArrayList<>();
        // 将10000-1存入list中
        for (int i = 10000; i >= 1; i--) {
            list.add(i);
        }

        list.parallelStream()// 获取并行流
                .sorted()// 按自然排序，即按数字从小到大排序
                .count();// count()是终止操作，有终止操作才会执行中间操作sorted()

        // 终止时间
        LocalTime end = LocalTime.now();

        // 时间间隔
        Duration duration = Duration.between(start, end);
        // 输出时间间隔毫秒值
        System.out.println(duration.toMillis());
    }

    public static void main(String[] args) {
        new  TestParallelStream().testStream();
        new  TestParallelStream().testParallelStream();

    }
}