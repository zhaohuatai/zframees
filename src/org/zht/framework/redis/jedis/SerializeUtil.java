/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.redis.jedis;

import java.io.*;
import org.zht.framework.util.LogUtil;
/**
 * redis 存取 序列化工具类 
 *  <br>序列化，反序列化方式采用jdk自带API ，
 *    后期意向改成thrift 或google protobuf
* @ClassName :SerializeUtil     
* @Description :   
* @createTime :2015年5月7日  上午9:28:36   
* @author ：zhaohuatai   
* @version :1.0
 */
public class SerializeUtil {
	/**
	 * 序列化
	 * @param value
	 * @return
	 */
    public static byte[] serialize(Object value) {
        if (value == null) {
            return null;
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
        	LogUtil.genErrorLog(SerializeUtil.class,"serialize",e.getMessage(), e);
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }

    /**
     * 反序列化
     * @param in
     * @return
     */
    public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }

    /**
     * 反序列化
     * @param in
     * @param requiredType
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] in, Class<T> requiredType) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
            }
        } catch (Exception e) {
        	LogUtil.genErrorLog(SerializeUtil.class,"deserialize",e.getMessage(), e);
        } finally {
            close(is);
            close(bis);
        }
        return (T) rv;
    }

    private static void close(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
            	LogUtil.genErrorLog(SerializeUtil.class,"close",e.getMessage(), e);
            }
    }

}
