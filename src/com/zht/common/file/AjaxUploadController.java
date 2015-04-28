package com.zht.common.file;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zht.framework.exception.FileNameLengthLimitExceededException;
import org.zht.framework.exception.InvalidExtensionException;
import org.zht.framework.message.WebUploaderError;
import org.zht.framework.message.webUploaderJaon;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FileUploadUtils;
@Controller 
@RequestMapping("/common/sys/file")
public class AjaxUploadController extends BaseController {
	private String jspPrefix="/common/sys/file/";
	
	@RequestMapping(value="/enterFileUploadPage")
	public String enterFileUploadPage() throws Exception {
		//		return "/common/sys/file/ajaxFileUpload";
		return "/common/sys/file/fileUploadPage";
	}
	
    private long maxSize = FileUploadUtils.DEFAULT_MAX_SIZE;
    //允许的文件内容类型
    private String[] allowedExtension = FileUploadUtils.DEFAULT_ALLOWED_EXTENSION;
    //文件上传下载的父目录
    private String baseDir = FileUploadUtils.getDefaultBaseDir();
    /**
     * @param request
     * @param files
     * @return
     */
    @RequestMapping(value = "ajaxUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxUpload(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "file", required = false) MultipartFile file) {
            String filename = file.getOriginalFilename();
            long size = file.getSize();
            try {
                String url = FileUploadUtils.upload(request, baseDir, file, allowedExtension, maxSize, true);
            } catch (IOException e) {
            	return ajaxDoneError("失败"+e.getMessage());
                //ajaxUploadResponse.add(filename, size, MessageUtils.message("upload.server.error"));
            } catch (InvalidExtensionException e) {
            	return ajaxDoneError("失败"+e.getMessage());
               // ajaxUploadResponse.add(filename, size, MessageUtils.message("upload.not.allow.extension"));
            } catch (FileUploadBase.FileSizeLimitExceededException e) {
            	return ajaxDoneError("失败"+e.getMessage());
               // ajaxUploadResponse.add(filename, size, MessageUtils.message("upload.exceed.maxSize"));
            } catch (FileNameLengthLimitExceededException e) {
            	return ajaxDoneError("失败"+e.getMessage());
               // ajaxUploadResponse.add(filename, size, MessageUtils.message("upload.filename.exceed.length"));
            }
            return ajaxDoneSuccess("成功");
    }
    /**
     * @param request
     * @param files
     * @return
     */
    @RequestMapping(value = "ajaxMultiUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxMultiUpload(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "files[]", required = false) MultipartFile[] files) {

        //The file upload plugin makes use of an Iframe Transport module for browsers like Microsoft Internet Explorer and Opera, which do not yet support XMLHTTPRequest file uploads.
        response.setContentType("text/plain");

        if (ArrayUtils.isEmpty(files)) {
//            return ajaxUploadResponse;
        }

        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            long size = file.getSize();

            try {
                String url = FileUploadUtils.upload(request, baseDir, file, allowedExtension, maxSize, true);
                String deleteURL = "/ajaxUpload/delete?filename=" + URLEncoder.encode(url, "UTF-8");
//                if (ImagesUtils.isImage(filename)) {
//                    ajaxUploadResponse.add(filename, size, url, url, deleteURL);
//                } else {
//                    ajaxUploadResponse.add(filename, size, url, deleteURL);
//                }
                continue;
            } catch (IOException e) {
                //ajaxUploadResponse.add(filename, size, MessageUtils.message("upload.server.error"));
                continue;
            } catch (InvalidExtensionException e) {
               // ajaxUploadResponse.add(filename, size, MessageUtils.message("upload.not.allow.extension"));
                continue;
            } catch (FileUploadBase.FileSizeLimitExceededException e) {
               // ajaxUploadResponse.add(filename, size, MessageUtils.message("upload.exceed.maxSize"));
                continue;
            } catch (FileNameLengthLimitExceededException e) {
               // ajaxUploadResponse.add(filename, size, MessageUtils.message("upload.filename.exceed.length"));
                continue;
            }
        }
        return null;
    }


    @RequestMapping(value = "ajaxUpload/delete", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxUploadDelete(
            HttpServletRequest request,
            @RequestParam(value = "filename") String filename) throws Exception {

        if (StringUtils.isEmpty(filename) || filename.contains("\\.\\.")) {
            return "";
        }
        filename = URLDecoder.decode(filename,"UTF-8");

        String filePath = FileUploadUtils.extractUploadDir(request) + "/" + filename;

        File file = new File(filePath);
        file.deleteOnExit();

        return "";
    }

}
