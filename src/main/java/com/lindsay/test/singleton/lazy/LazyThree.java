package com.lindsay.test.singleton.lazy;

/**
 * LazyThree
 *
 * @author Lindsay
 * @date 2019/4/12 21:08
 **/
public class LazyThree {

    private static  boolean initialized = false;

    // 默认使用LazyThree的时候, 会先初始化内部类
    // 如果没有使用的话, 内部类是不加载的

    private LazyThree() {

        synchronized (LazyThree.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被侵犯");
            }
        }

    }

    public static final LazyThree getInstance() {
        return LazyHolder.LAZY;
    }

    private static class LazyHolder {
        private static final LazyThree LAZY = new LazyThree();
    }

}
