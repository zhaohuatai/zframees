/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.regex.Pattern;

public class FileUtil {
	public static String[] getFileList(String dir) {
		try {
			File parent = new File(dir);
			if (!parent.isAbsolute() || !parent.isDirectory()) {
				return null;
			}
			return parent.list();
		} catch (Exception e) {
			return null;
		}
	}

	public static String[] getFileList(final String dir, final String pattern) {
		try {

			File parent = new File(dir);
			if (!parent.isAbsolute() || !parent.isDirectory()) {
				return null;
			}
			final Pattern namePattern = Pattern.compile(pattern);
			return parent.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if (namePattern.matcher(name).matches()) {
						return true;
					} else {
						return false;
					}
				}
			});

		} catch (Throwable te) {
			return null;
		}
	}
	 public static boolean deleteFile(String path) {
		  try {
		   File file = new File(path);
		   if(file.exists()){
			   file.delete();
		   }
		   
		   return true;
		  } catch (Exception e) {
		   return false;
		  }
		 }
	   public boolean deleteDirectory(File dir) {
	        File[] entries = dir.listFiles();
	        int sz = entries.length;
	        for (int i = 0; i < sz; i++) {
	            if (entries[i].isDirectory()) {
	                if (!deleteDirectory(entries[i])) {
	                    return false;
	                }
	            } else {
	                if (!entries[i].delete()) {
	                    return false;
	                }
	            }
	        }
	        if (!dir.delete()) {
	            return false;
	        }
	        return true;
	    }
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}
	public static String getExtend(String filename, String defaultValue) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i+1)).toLowerCase();
			}
		}
		return defaultValue.toLowerCase();
	}
	   public static void createDir(String dirParth){
		   File fp = new File(dirParth);  
	       if (!fp.exists()) {  
	           fp.mkdirs();// 目录不存在的情况下，创建目录。  
	       }  
	   }
		public static void fileChannelCopy(File s, File t) {
			FileInputStream fi = null;
			FileOutputStream fo = null;
			FileChannel in = null;
			FileChannel out = null;
			try {
				fi = new FileInputStream(s);
				fo = new FileOutputStream(t);
				in = fi.getChannel();// 得到对应的文件通道
				out = fo.getChannel();// 得到对应的文件通道
				in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fi.close();
					in.close();
					fo.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		
		 public static byte[] getFoleBytesByIO( File f)  {  
			 if(f==null||!f.exists()){
			  		return null;
			  	}
		        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());  
		        BufferedInputStream in = null;  
		        try {  
		            in = new BufferedInputStream(new FileInputStream(f));  
		            int buf_size = 1024;  
		            byte[] buffer = new byte[buf_size];  
		            int len = 0;  
		            while (-1 != (len = in.read(buffer, 0, buf_size))) {  
		                bos.write(buffer, 0, len);  
		            }  
		            return bos.toByteArray();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        } finally {  
		            try {  
		                in.close();    bos.close();  
		            } catch (IOException e) {  
		                e.printStackTrace();  
		            }  
		          
		        }
				return null;  
		    }  
		 
		  public static byte[] getFoleBytesByNIO(File f ) throws IOException {  
			  	if(f==null||!f.exists()){
			  		return null;
			  	}
		        FileChannel channel = null;  
		        FileInputStream fs = null;  
		        try {  
		            fs = new FileInputStream(f);  
		            channel = fs.getChannel();  
		            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());  
		            while ((channel.read(byteBuffer)) > 0) {  
		                // do nothing  
		                // System.out.println("reading");  
		            }  
		            return byteBuffer.array();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		            throw e;  
		        } finally {  
		            try {  
		                channel.close();  
		            } catch (IOException e) {  
		                e.printStackTrace();  
		            }  
		            try {  
		                fs.close();  
		            } catch (IOException e) {  
		                e.printStackTrace();  
		            }  
		        }  
		    }  
		  
		  @SuppressWarnings("resource")
		public static byte[] getFoleBytesByMPPER(String filename) throws IOException {  
			  
		        FileChannel fc = null;  
		        try {  
		            fc = new RandomAccessFile(filename, "r").getChannel();  
		            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,   fc.size()).load();  
		            System.out.println(byteBuffer.isLoaded());  
		            byte[] result = new byte[(int) fc.size()];  
		            if (byteBuffer.remaining() > 0) {  
		                // System.out.println("remain");  
		                byteBuffer.get(result, 0, byteBuffer.remaining());  
		            }  
		            return result;  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		            throw e;  
		        } finally {  
		            try {  
		                fc.close();  
		            } catch (IOException e) {  
		                e.printStackTrace();  
		            }  
		        }  
		    }  
}
