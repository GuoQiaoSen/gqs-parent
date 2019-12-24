package com.gqs.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 郭乔森
 * @create 2019-12-11 18:24
 */
public class MapExample {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

//        bianli1(map);
        bianliAndRemove(map);
    }

    /**
     * 遍历map，方式1
     */
    public static void bianli1(Map<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println("key:" + key + "value:" + map.get(key));

        }
    }

    /**
     * 遍历map，方式2
     * 并删除特定值
     *
     * @param map
     */
    public static void bianliAndRemove(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String key = entry.getKey();
            if (key.equals("1")) {
                it.remove();
            }
        }

        for (String key : map.keySet()) {
            System.out.println("key:" + key + "value:" + map.get(key));

        }
    }
}
