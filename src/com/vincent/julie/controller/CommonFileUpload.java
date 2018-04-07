package com.vincent.julie.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.vincent.julie.CodeConfig;
import com.vincent.julie.Config;
import com.vincent.julie.MsgConfig;
import com.vincent.julie.utils.CreateFileUtil;
import com.vincent.julie.utils.ResponseUtils;

/**
 * @Title: CommonFileUpload.java
 * @Package com.vincent.julie.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: Vinent QQ:1032006226
 * @date: 2018年4月5日 下午3:28:25
 * @version V1.0
 * @Copyright: 2018 注意：本内容仅限于是我写的
 */
@Controller
@RequestMapping("common/fileUpload")
public class CommonFileUpload {

	/**
	 * 上传一张图
	 */
	/**
	 * 上传一张图片，返回图片路径
	 * @param user_id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "oneImgUpload", method = RequestMethod.POST)
	public void uploadImageOne(@RequestParam("user_id") int user_id, HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			// 用户文件路径
			String userImgDir = Config.MYWEB_NETWORK_FILES_USER_FILES + "/" + String.valueOf(user_id) + "/" + "imageFiles";
			String fullDirPath = Config.MYWEB_NETWORK_FEILE_ROOT_DIRECTORY + userImgDir;
			CreateFileUtil.createDir(fullDirPath);
			
			 //创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest =           
	                    multipartResolver.resolveMultipart(request);  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames(); 
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	                        //重命名上传后的文件名  
	                        String fileName = file.getOriginalFilename();  
	                        fileName = System.currentTimeMillis()+fileName;
	                        //定义上传路径  
	                        File localFile = new File(fullDirPath,fileName);  
	                        userImgDir = userImgDir+"/"+localFile.getName();
	                        try {
								file.transferTo(localFile);
							} catch (IllegalStateException e) {
								e.printStackTrace();
								ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION, MsgConfig.COMMON_FILE_UPLOAD_FAIL);
							} catch (IOException e) {
								 ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION, MsgConfig.COMMON_FILE_UPLOAD_FAIL);
								e.printStackTrace();
							}  
	                    }  
	                }  
	            }  
	            JSONObject json = new JSONObject();
	            json.put("path", userImgDir);
	            ResponseUtils.renderJsonDataSuccess(response, MsgConfig.COMMON_FILE_UPLOAD_SUCCESS, json);
	        }  
		} catch (Exception e) {
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_FILE_UPLOAD_FAIL);
		}
	}
	
	
    /**
     * 多文件上传
     * @param user_id
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="multifileUpload",method=RequestMethod.POST)  
    public void fildUpload(@RequestParam("user_id")int user_id,  
            HttpServletRequest request,HttpServletResponse response){  
          
    	try {
			// 用户文件路径
			String userImgDir = Config.MYWEB_NETWORK_FILES_USER_FILES + "/" + String.valueOf(user_id) + "/" + "imageFiles";
			String fullDirPath = Config.MYWEB_NETWORK_FEILE_ROOT_DIRECTORY + userImgDir;
			CreateFileUtil.createDir(fullDirPath);
			List<String> resultPaths = new ArrayList<String>();
			 //创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest =           
	                    multipartResolver.resolveMultipart(request);  
	            //取得request中的所有文件名  
//	            Iterator<String> iter = multiRequest.getFileNames();
	            
	            MultiValueMap<String, MultipartFile> iter = multiRequest.getMultiFileMap();
	            System.out.println("size = "+iter.size());
	            for (Map.Entry<String, List<MultipartFile>> entry : iter.entrySet()) {
	                //取得上传文件
	                MultipartFile file = multiRequest.getFile(entry.getKey());
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	                        //重命名上传后的文件名  
	                        String fileName = file.getOriginalFilename();  
	                        fileName = System.currentTimeMillis()+fileName;
	                        //定义上传路径  
	                        File localFile = new File(fullDirPath,fileName);  
	                       String newNetUrl = userImgDir+"/"+localFile.getName();
	                        try {
								file.transferTo(localFile);
								resultPaths.add(newNetUrl);
							} catch (IllegalStateException e) {
								e.printStackTrace();
								ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION, MsgConfig.COMMON_FILE_UPLOAD_FAIL);
							} catch (IOException e) {
								 ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION, MsgConfig.COMMON_FILE_UPLOAD_FAIL);
								e.printStackTrace();
							}  
	                    }  
	                } 
	            }
	            JSONObject json = new JSONObject();
	            json.put("path", resultPaths);
	            ResponseUtils.renderJsonDataSuccess(response, MsgConfig.COMMON_FILE_UPLOAD_SUCCESS, json);
	        }  
		} catch (Exception e) {
			ResponseUtils.renderJsonDataFail(response, CodeConfig.SERVICE_EXCEPTION,
					MsgConfig.COMMON_FILE_UPLOAD_FAIL);
		}
    }  

}
