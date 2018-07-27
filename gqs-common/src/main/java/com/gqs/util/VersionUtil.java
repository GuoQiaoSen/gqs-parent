package com.gqs.util;

/**
 * VersionUtil 锛屾瘮杈冪増鏈彿
 */
public class VersionUtil {
	
	public static void main(String[] args) throws Exception {
		String version1 = "1.12.3";
		String version2 = "1.2.11";
		int compareVersion = compareVersion(version1, version2);
		System.out.println(compareVersion);
	}

	/**
	 * 姣旇緝鐗堟湰鍙风殑澶у皬,鍓嶈�呭ぇ鍒欒繑鍥炰竴涓鏁�,鍚庤�呭ぇ杩斿洖涓�涓礋鏁�,鐩哥瓑鍒欒繑鍥�0
	 *
	 * @param version1
	 * @param version2
	 * @return
	 * @throws Exception
	 */
	public static int compareVersion(String version1, String version2) throws Exception {
		if (version1 == null || version2 == null) {
			throw new Exception("compareVersion error:illegal params.");
		}
		// 鍒囧壊鐐� "."锛�
		String[] versionArray1 = version1.split("\\.");
		String[] versionArray2 = version2.split("\\.");
		int idx = 0;
		// 鍙栨渶灏忛暱搴﹀��
		int minLength = Math.min(versionArray1.length, versionArray2.length);
		int diff = 0;
		// 鍏堟瘮杈冮暱搴� 鍐嶆瘮杈冨瓧绗�
		while (idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0 && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
			++idx;
		}
		// 濡傛灉宸茬粡鍒嗗嚭澶у皬锛屽垯鐩存帴杩斿洖锛屽鏋滄湭鍒嗗嚭澶у皬锛屽垯鍐嶆瘮杈冧綅鏁帮紝鏈夊瓙鐗堟湰鐨勪负澶э紱
		diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
		return diff;
	}

}
