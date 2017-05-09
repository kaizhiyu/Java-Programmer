package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static void test1() throws NoSuchAlgorithmException{
		String str = "I Love You";
		//md5是一种加密算法,sha也是一种加密算法
		MessageDigest md = MessageDigest.getInstance("md5");
		byte[] buf = md.digest(str.getBytes());
		//将字节数组转换成一个字符串
		//BASE64Encoder可以将任意字节数组转换成字符串
		BASE64Encoder encoder = new BASE64Encoder();
		String str2 = encoder.encode(buf);
		System.out.println(str2);		
	}
	public static String encrypt(String str) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("md5");
		byte[] buf = md.digest(str.getBytes());
		//将字节数组转换成一个字符串
		//BASE64Encoder可以将任意字节数组转换成字符串
		BASE64Encoder encoder = new BASE64Encoder();
		String str2 = encoder.encode(buf);
		return str2;
	}
	public static void main(String[] args) throws Exception {
		test1();
	}
}
