package com.zht.common.shiro.util;

import org.apache.shiro.codec.Base64;


import org.apache.shiro.codec.Hex; 
import org.apache.shiro.crypto.hash.Md5Hash; 
import org.zht.framework.consts.GlobleConstant;
import org.zht.framework.util.UUIDUtil;

public class EndecryptUtils {
	  /**
	   * userName :{admin}
		 page_password :{123456}
		 page_salt :{admin@zhtframework_94DABGioQOq2tTUO0AXYow}
		 //83da8e102358e921cc1ee088138b74d4
		 password_from_page :{3335672fa546d2d2c8fa5a102027b00d}
		 
		 genRandomSalt :{3293523627878424jm4eSkDtFd4KSM8yWSMqp}
		 encptyedPasswod :{fe6f642c4e184a504e142be37eaa4a59}
	   * @param argd
	   */
    public static void main(String[] argd){//eef3a22a128d5adb5699e3c7da7a6fc8
    	String page_salt=GlobleConstant.pageSalt;
    	String userName="admin";
    	String page_password="123456";
    	String password_from_page= new Md5Hash(page_password,userName+page_salt,1).toString();
    	 
    	 System.out.println("userName :{"+userName+"}");
    	 System.out.println("page_salt :{"+userName+page_salt+"}");
    	 System.out.println("page_password :{"+page_password+"}");
    
    	 
    	 System.out.println("password_from_page :{"+password_from_page+"}");
    	 
    	 String genSalt=genRandomSalt();
    	 String encptyPass=genPassword(password_from_page,genSalt,2);
    	 
    	 System.out.println("genRandomSalt :{"+genSalt+"}");
    
    	 System.out.println("encptyedPasswod :{"+encptyPass+"}");
    }
    
    //15+22+32
    public static String genSalt(String userName){
//    	String salt=System.nanoTime()+UUIDUtil.base58Uuid()+new Md5Hash((""+userName).trim()).toString();;//
    	String salt=System.nanoTime()+UUIDUtil.base58Uuid();//
    	return salt;
    }
    public static String genRandomSalt(){
    	String salt=System.nanoTime()+UUIDUtil.base58Uuid();//
    	return salt;
    }
    
    /**
     * 注意：Md5Hash 把salt的字节放到要加密 整体的 前端 即 dist(salt+password),而不是 password+{salt}的形式
     * 所以页面MD5加密时注意 
     * @param password
     * @param salt
     * @return
     */
    public static String genPassword(String password,String salt,int times){
    	String password_cipherText2= new Md5Hash(password,salt,times).toHex().toString(); 
    	return password_cipherText2;
    }
    
	 /** 
     * base64进制加密 
     * 
     * @param password 
     * @return 
     */ 
    public static String encrytBase64(String password) { 
    	if(password==null){
    		return null;
    	}
        byte[] bytes = password.getBytes(); 
        return Base64.encodeToString(bytes); 
    } 
    /** 
     * base64进制解密 
     * @param cipherText 
     * @return 
     */ 
    public static String decryptBase64(String cipherText) {
    	if(cipherText==null){
    		return null;
    	}
        return Base64.decodeToString(cipherText); 
    } 
    /** 
     * 16进制加密 
     * @param password 
     * @return 
     */ 
    public static String encrytHex(String password) { 
    	if(password==null){
    		return null;
    	}
        byte[] bytes = password.getBytes(); 
        return Hex.encodeToString(bytes); 
    } 
    /** 
     * 16进制解密 
     * @param cipherText 
     * @return 
     */ 
    public static String decryptHex(String cipherText) { 
    	if(cipherText==null){
    		return null;
    	}
        return new String(Hex.decode(cipherText)); 
    } 
}
