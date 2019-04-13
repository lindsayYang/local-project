package com.lindsay.test.singleton.test;

import com.lindsay.test.singleton.hungry.Hungry;
import com.lindsay.test.singleton.lazy.LazyOne;
import com.lindsay.test.singleton.lazy.LazyThree;
import com.lindsay.test.singleton.lazy.LazyTwo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * ThreadSafeTest
 *
 * @author Lindsay
 * @date 2019/4/12 20:33
 **/
public class ThreadSafeTest {

    public static void main(String[] args) {

        int count = 200;
        // 实现并发
        CountDownLatch latch = new CountDownLatch(count);

        // final Set<Hungry> syncSet = Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < count; i++) {
            new Thread() {
                @Override
                public void run() {
                    // syncSet.add(Hungry.getInstance());

                    try {
                        latch.await();
                        Object obj = LazyThree.getInstance();
                        System.out.println(System.currentTimeMillis() + ":" + obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            latch.countDown();
        }



    }

}
