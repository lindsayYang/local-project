package com.lindsay.test.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test
 *
 * @author Lindsay
 * @date 2019/3/7 15:44
 **/
public class Test {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();

        list.add(1L);

        list.add(8L);

        list.add(2L);

        List<Long> collect = list.stream().sorted().collect(Collectors.toList());
    }

}
