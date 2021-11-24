package com.yuyi.util;

import java.util.UUID;

/**
 * 生成UUID的工具類
 * @author 育奕
 *
 */
public class CommonsUtils {

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
