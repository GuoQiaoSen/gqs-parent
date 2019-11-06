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
//        listExample();
        removeExample();
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

    /**
     * 遍历集合
     * 根据特定条件删除集合中的某个元素时，删除操作后被删除的元素下标之后的元素都会前移1，
     * 所以要将i减一，否则遍历会遗漏调执行删除后那个下标对应的元素
     */
    public static void removeExample() {
        List<Long> list = new ArrayList<Long>();
        list.add(1L);
        list.add(3L);
        list.add(4L);
        list.add(5L);

        System.out.println("删除前：" + list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 1) {
                list.remove(i);
                --i;
            }
        }
        System.out.println("删除后：" + list);
    }
}
