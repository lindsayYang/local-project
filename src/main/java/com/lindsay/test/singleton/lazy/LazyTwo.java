package com.lindsay.test.singleton.lazy;

/**
 * LazyOne
 *
 * @author Lindsay
 * @date 2019/4/12 20:47
 **/
public class LazyTwo {

    private LazyTwo() {}

    private static LazyTwo lazyOne = null;

    public static synchronized LazyTwo getInstance() {
        if (lazyOne == null) {
            lazyOne = new LazyTwo();
        }

        return lazyOne;
    }

}
