package com.lindsay.test.singleton.hungry;

/**
 * Hungry
 *
 * @author Lindsay
 * @date 2019/4/12 20:26
 **/
public class Hungry {

    private Hungry() {}

    // 先静态, 后动态
    // 先属性, 后方法
    // 先上后下

    private static final Hungry hungry = new Hungry();

    public static Hungry getInstance() {

        return hungry;
    }

}
