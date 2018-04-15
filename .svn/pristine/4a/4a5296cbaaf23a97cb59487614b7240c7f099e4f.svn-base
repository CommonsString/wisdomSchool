package com.cj.witcommon.entity;

import java.util.UUID;

/**
 * 
 * @author zhouwei
 *
 */
public final class SLIDUtil {

	// 根据用户类型生成uuid
	public static final String newUUID(String userKey) {
		if ("1".equals(userKey)) {
			return systemAdminUniqueId();
		} else if ("2".equals(userKey)) {
			return schoolAdminUniqueId();
		} else if ("3".equals(userKey)) {
			return SLIDUtil.teacherUniqueId();
		} else if ("4".equals(userKey)) {
			return teacherUniqueId();
		} else if ("5".equals(userKey)) {
			return patriarchUniqueId();
		} else {
			return uuid();
		}
	}

	// 系统管理员
	public static String systemAdminUniqueId() {
		return "1" + uuid();
	}

	// 学校管理员
	public static String schoolAdminUniqueId() {
		return "2" + uuid();
	}

	// 老师
	public static String teacherUniqueId() {
		return "3" + uuid();
	}

	// 学生
	public static String studentUniqueId() {
		return "4" + uuid();
	}

	// 家长
	public static String patriarchUniqueId() {
		return "5" + uuid();
	}

	// 生成UUID
	public static String uuid() {
		return (UUID.randomUUID().toString().replaceAll("-", ""));
	}

	public static String token() {
		return uuid();
	}

	public static boolean validateID(long id) {
		return id > 0;
	}

	public static long toId(String number) {
		try {
			return Long.valueOf(number);
		} catch (Exception e) {
			return -1;
		}
	}
}
