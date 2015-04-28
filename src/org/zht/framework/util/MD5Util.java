/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.util;

import java.security.MessageDigest;

import org.apache.shiro.crypto.hash.Md5Hash;
public class MD5Util {

	public static String encodeWithSalt( String pssword, String salt){
		//pssword = pssword + "{" + salt + "}";// 密码和盐值组合方式
		pssword = pssword + salt;// 密码和盐值组合方式
		return encode(pssword);
	}

	public static String encode(String str){
		try {
			byte[]strBytes = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(strBytes);
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			int j = digest.length;
			char strs[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = digest[i];
				strs[k++] = hexDigits[b >> 4 & 0xf];
				strs[k++] = hexDigits[b & 0xf];
			}
			return new String(strs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param args
	 * 46564af66321701225b7f04e19bfcc08
	 * 46564af66321701225b7f04e19bfcc08
	 */
	public static void main(String[] args) {
		String pass = "123456";
		String salt = "shiro";//a05bd3699caee8947540997aedb064f6
		String result = null;
//		result = encodeWithSalt(pass, salt);
		result = encodeWithSalt(pass,salt);
		System.out.println(result);
		System.out.println("----------------------------");
		 String password_cipherText= new Md5Hash(pass,salt,1).toHex(); 
    	 System.out.println(password_cipherText);//eef3a22a128d5adb5699e3c7da7a6fc8
	}

}
