package com.gqs.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author 郭乔森
 * @create 2019-04-26 17:16
 */
public class ListExample {

    public static void main(String[] args) {
        listExample();
    }

    /**
     * 判断一个元素是否存在于list中
     */
    public static void listExample() {
        List<Long> list = new ArrayList<Long>();
        // 添加元素
        list.add(11L);
        list.add(44L);
        // 在指定位置插入元素，其后的元素依次后移1
        list.add(0, 2L);
        // 判断是否存在某元素
        boolean isCon = list.contains(1L);
        // 获取元素首次出现的位置下标
        list.indexOf(1L);
        // 获取元素最后出现的位置下标
        list.lastIndexOf(2L);
    }
}
