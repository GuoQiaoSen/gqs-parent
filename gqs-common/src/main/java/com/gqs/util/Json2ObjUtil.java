package com.gqs.util;

import com.google.gson.Gson;
import com.gqs.Department;

/**
 * 描述:
 * json与对象互转
 *
 * @author 郭乔森
 * @create 2019-05-18 11:04
 */
public class Json2ObjUtil {
    public static void main(String[] args) {
        String jsonStr = "{\n" +
                "    \"deptName\": \"郭乔森\",\n" +
                "    \"deptId\": 133338,\n" +
                "    \"userList\": [\n" +
                "        {\n" +
                "            \"name\": \"郭乔森\",\n" +
                "            \"age\": 18\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"第三款呢\",\n" +
                "            \"age\": 18\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        // json转对象
        Department department = json2Obj(jsonStr, Department.class);
        System.out.println(department.toString());

        // 对象转json
        String jsonStr2 = obj2Json(department);
        System.out.println(jsonStr2);
    }

    /**
     * json转对象
     *
     * @param json
     * @param beanType
     * @param <T>
     * @return
     */
    private static <T> T json2Obj(String json, Class<T> beanType) {
        Gson gson = new Gson();
        T t = gson.fromJson(json, beanType);
        return t;
    }

    /**
     * 对象转json
     *
     * @param obj
     * @return
     */
    private static String obj2Json(Object obj) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(obj);
        return jsonStr;
    }
}