package com.lindsay.test.test;

/**
 * Prototype
 *
 * @author Lindsay
 * @date 2019/4/9 17:01
 **/
public interface Prototype {

    void  setAttr(String attr);

    Prototype clone();
}
