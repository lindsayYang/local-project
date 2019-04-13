package com.lindsay.test.test;

/**
 * TestPrototype
 *
 * @author Lindsay
 * @date 2019/4/9 17:03
 **/
public class TestPrototype {

    public static void main(String[] args) {

        ConcretePrototype obj1 = new ConcretePrototype();

        Prototype obj2 = obj1.clone();

        System.out.println(obj1 + "---" + obj2);

    }

}
