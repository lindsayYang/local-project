package com.lindsay.test.test;

/**
 * VehicleTest
 *
 * @author Lindsay
 * @date 2019/4/19 14:15
 **/
public class VehicleTest {

    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = sb1;

        System.out.println(sb1 == sb2 );  // true
    }

}
