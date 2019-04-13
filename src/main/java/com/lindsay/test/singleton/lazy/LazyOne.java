package com.lindsay.test.singleton.lazy;

/**
 * LazyOne
 *
 * @author Lindsay
 * @date 2019/4/12 20:47
 **/
public class LazyOne {

    private LazyOne() {}

    private static LazyOne lazyOne = null;

    public static LazyOne getInstance() {
        if (lazyOne == null) {
            lazyOne = new LazyOne();
        }

        return lazyOne;
    }

}
