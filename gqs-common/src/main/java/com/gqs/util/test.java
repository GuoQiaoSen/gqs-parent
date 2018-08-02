package com.gqs.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gqs.User;

public class test {

	public static void main(String[] args) {
		// bubbleSort();

	}

	public static void bubbleSort() {
		int[] a = { 5, 1, 87, 1, 9, 0, 324, 55 };
		int len = a.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - i - 1; j++) {// 注意第二重循环的条件
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	public <T> T jsonToBean(Class<T> beanClass) {
		Gson gson = new Gson();

		String jsonStr = "{\"user\":[{\"name\":\"sdjfl\",\"sex\":\"nan\"},{\"name\":\"sdjfl\",\"sex\":\"nan\"}]}";

		User customerDefault = gson.fromJson(jsonStr, new TypeToken<User>() {
		}.getType());
		return null;
	}
}
