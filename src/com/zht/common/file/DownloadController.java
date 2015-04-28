/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.file;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zht.framework.web.utils.DownloadUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.net.URLDecoder;

@Controller
public class DownloadController {


    @RequestMapping(value = "/download")
    public String download(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "filename") String filename) throws Exception {

        filename = filename.replace("/", "\\");

        if (StringUtils.isEmpty(filename) || filename.contains("\\.\\.")) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("您下载的文件不存在！");
            return null;
        }
        filename = URLDecoder.decode(filename, "UTF-8");
        
        String projectPath=request.getServletContext().getRealPath("/");
        
        String filePath = projectPath + File.separator + filename;

        DownloadUtils.download(request, response, filePath, filename);

        return null;
    }




}
