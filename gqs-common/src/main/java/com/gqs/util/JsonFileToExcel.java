package com.gqs.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JsonFileToExcel {
    public static void main(String[] args) throws Exception {
        // 设置文件路径
        String filePath = "D:\\zxx_paper_answer_1206-1w+.json";  // 读取txt文件的路径

        // 读取文件并将每行 JSON 字符串合并为 JsonArray
        JSONArray jsonArray = readJsonFromFile(filePath);

        // 获取所有表头字段（收集所有的键）
        Set<String> headerSet = new LinkedHashSet<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            headerSet.addAll(jsonObject.keySet()); // 收集当前对象的所有字段名
        }

        // 将 Set 转换为 List 并按字段名排序（如果需要按字母顺序排序）
        List<String> headers = new ArrayList<>(headerSet);
        // headers.sort(Comparator.naturalOrder()); // 如果需要按字母顺序排序，可以取消注释

        // 转换为 List<List<Object>> 格式
        List<List<Object>> data = jsonArrayToList(jsonArray, headers);

        // 设置文件输出路径为 D 盘
        String excelFilePath = "D:\\【2024年度中职三科统编教材使用监测】下问卷数据-20241206-100w+.xlsx";

        // 将数据写入 Excel 文件，确保表头横向输出
        EasyExcel.write(excelFilePath).sheet("Sheet1").head(createHeader(headers)).doWrite(data);

        System.out.println("Excel 文件已保存到: " + excelFilePath);
    }

    // 从文件读取每行 JSON 字符串并合并为 JsonArray
    private static JSONArray readJsonFromFile(String filePath) throws IOException {
        JSONArray jsonArray = new JSONArray();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 跳过空行
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                // 替换不能解析的字符
                line = line.replace("{\"$oid\":", "").replace("},", ",")
                        .replace("ObjectId(", "")
                        .replace("ISODate(", "")
                        .replace("),", ",")
                        .replace(") }", "}")
                        .replace(")}", "}")
                        .replace("\"creator\":{", "").replace("{\"$date\":", "")
                        .replace("}}", "}");
                // 检查字符串是否以逗号结尾
                if (line.endsWith(",")) {
                    line = line.substring(0, line.length() - 1); // 去掉最后一个字符
                }
                // 每行是一个 JSON 字符串，转换为 JSONObject 并添加到 JsonArray
                JSONObject jsonObject = null;
                try {
                    jsonObject = JSON.parseObject(line);
                } catch (Exception e) {
                    System.out.println("错误数据：" + line);
                    throw e;
                }
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }

    // 将 JSON 数组转换为 EasyExcel 可接受的 List<List<Object>> 格式
    private static List<List<Object>> jsonArrayToList(JSONArray jsonArray, List<String> headers) {
        List<List<Object>> data = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            List<Object> row = new ArrayList<>();
            // 按照表头顺序填充数据
            for (String header : headers) {
                row.add(String.valueOf(jsonObject.getOrDefault(header, null))); // 如果当前对象没有该字段，则填充 null
            }
            data.add(row);
        }
        return data;
    }

    // 创建 Excel 表头
    private static List<List<String>> createHeader(List<String> headers) {
        List<List<String>> header = new ArrayList<>();
        // 将表头字段作为一行列头
        header.add(headers);
        return header;
    }
}