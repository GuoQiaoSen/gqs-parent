package com.gqs.util;

import java.util.UUID;

public class UUIDRandom {

	/**
	 * 生成uuid
	 */
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uuid);
	}
}
