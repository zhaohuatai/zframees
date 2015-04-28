/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;

public class AntZipUtil {

	
    public static  void zip(File file, String savePath, String saveName) throws IOException {
    	FileOutputStream fout=new FileOutputStream(savePath + "/" + saveName);
        ZipOutputStream zos = new ZipOutputStream(fout);
        zos.setEncoding("gbk");
        addToZip(file, zos, "");
        zos.close();
        fout.close();
    }
    private static  void addToZip(File file, ZipOutputStream zos, String zipPath) throws IOException {
        if (file.isDirectory()) {
            ZipEntry en = new ZipEntry(zipPath + file.getName() + "/");
            zos.putNextEntry(en);
            zos.closeEntry();
            File[] files = file.listFiles();
            for (File f : files) {
                addToZip(f, zos, zipPath + file.getName() + "/");
            }
        } else {
        	FileInputStream fis=new FileInputStream(file);
        	
            ZipEntry childEntry = new ZipEntry(zipPath + file.getName());
            zos.putNextEntry(childEntry);
            IOUtils.copy(fis, zos);
            zos.closeEntry();
            fis.close();
        }
    }

}
