package com.gqs.example;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * 描述:
 *
 * @author 郭乔森
 * @create 2019-04-26 17:16
 */
public class ListExample {

    public static void main(String[] args) {
//        listExample();
//        removeExample();
//        sort();
        getRepeat();
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

    /**
     * 对list进行排序
     */
    public static void sort() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(1);
        list.add(5);
        list.add(4);
        System.out.println("排序前：" + list.toString());

        Collections.sort(list);
        System.out.println("排序后：" + list.toString());
    }

    /**
     * 判断list中是否包含重复元素
     * <p>
     * 在开发工作中，我们有时需要去判断List集合中是否含有重复的元素
     * 我们不需要找出重复的元素，我们只需要返回一个Boolean类型就可以了
     * 如果使用循环遍历的方式，将会消耗大量的性能
     * 我们只需要将List集合转化为Set集合，再进行比较就可以实现了
     */
    public static void isRepeat() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("a");
        list.add("c");
        HashSet<String> set = new HashSet<String>(list);
        Boolean result = set.size() == list.size() ? true : false;
        System.out.println(result);
    }

    /**
     * 获取list集合中重复的元素
     */
    public static void getRepeat() {
        //创建一个list并加入元素
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("a");
        for (String str : list) {
            System.out.println(str);
        }

        //将list放入set中对其去重
        Set<String> set = new HashSet<String>(list);
        System.out.println("---------------------------------------");

        //获得list与set的差集
        Collection rs = CollectionUtils.disjunction(list, set);
        //将collection转换为list
        List<String> list1 = new ArrayList<String>(rs);
        for (String str : list1) {
            System.out.println(str);
        }
    }
}
