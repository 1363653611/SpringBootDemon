package com.zbcn.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 加密工具类
 */
public class MD5Utils {

	private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);

	//用于加密的字符
	private static char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'A', 'B', 'C', 'D', 'E', 'F' };

	private static final String  MD5_ALGORITHM= "MD5";

	/**
	 * md5 加密算法
	 * @param str
	 * @return
	 */
	public static String encrypt(String str){
		//使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
		byte[] btInput = str.getBytes();
		//信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
		try {
			MessageDigest mdInst = MessageDigest.getInstance(MD5_ALGORITHM);
			//MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
			byte[] digest = mdInst.digest(btInput);

			int j = digest.length;
			char md[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {   //  i = 0
				byte byte0 = digest[i];  //95
				md[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
				md[k++] = md5String[byte0 & 0xf];   //   F
			}

			//返回经过加密后的字符串
			return new String(md);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("md5 加密失败.{}",str,e);
		}
		return null;
	}


}
