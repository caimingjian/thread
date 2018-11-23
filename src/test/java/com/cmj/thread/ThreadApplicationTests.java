package com.cmj.thread;

import com.cmj.thread.concurrent.ThreadPoolTask;
import com.cmj.thread.concurrent.ScheduleTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadApplicationTests {

    @Autowired
    private ThreadPoolTask task;

    @Test
    public void test() throws Exception {

        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();

    }

    @Autowired
    private ScheduleTask task2;

    @Test
    public void test2(){
        task2.test();
    }

}
