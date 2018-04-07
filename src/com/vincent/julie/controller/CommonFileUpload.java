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
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author: Vinent QQ:1032006226
 * @date: 2018��4��5�� ����3:28:25
 * @version V1.0
 * @Copyright: 2018 ע�⣺�����ݽ���������д��
 */
@Controller
@RequestMapping("common/fileUpload")
public class CommonFileUpload {

	/**
	 * �ϴ�һ��ͼ
	 */
	/**
	 * �ϴ�һ��ͼƬ������ͼƬ·��
	 * @param user_id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "oneImgUpload", method = RequestMethod.POST)
	public void uploadImageOne(@RequestParam("user_id") int user_id, HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			// �û��ļ�·��
			String userImgDir = Config.MYWEB_NETWORK_FILES_USER_FILES + "/" + String.valueOf(user_id) + "/" + "imageFiles";
			String fullDirPath = Config.MYWEB_NETWORK_FEILE_ROOT_DIRECTORY + userImgDir;
			CreateFileUtil.createDir(fullDirPath);
			
			 //����һ��ͨ�õĶಿ�ֽ�����  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();  
	        //�ж� request �Ƿ����ļ��ϴ�,���ಿ������  
	        if(multipartResolver.isMultipart(request)){  
	            //ת���ɶಿ��request    
	            MultipartHttpServletRequest multiRequest =           
	                    multipartResolver.resolveMultipart(request);  
	            //ȡ��request�е������ļ���  
	            Iterator<String> iter = multiRequest.getFileNames(); 
	            while(iter.hasNext()){  
	                //ȡ���ϴ��ļ�  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //ȡ�õ�ǰ�ϴ��ļ����ļ�����  
	                    String myFileName = file.getOriginalFilename();  
	                    //������Ʋ�Ϊ����,˵�����ļ����ڣ�����˵�����ļ�������  
	                    if(myFileName.trim() !=""){  
	                        //�������ϴ�����ļ���  
	                        String fileName = file.getOriginalFilename();  
	                        fileName = System.currentTimeMillis()+fileName;
	                        //�����ϴ�·��  
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
     * ���ļ��ϴ�
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
			// �û��ļ�·��
			String userImgDir = Config.MYWEB_NETWORK_FILES_USER_FILES + "/" + String.valueOf(user_id) + "/" + "imageFiles";
			String fullDirPath = Config.MYWEB_NETWORK_FEILE_ROOT_DIRECTORY + userImgDir;
			CreateFileUtil.createDir(fullDirPath);
			List<String> resultPaths = new ArrayList<String>();
			 //����һ��ͨ�õĶಿ�ֽ�����  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();  
	        //�ж� request �Ƿ����ļ��ϴ�,���ಿ������  
	        if(multipartResolver.isMultipart(request)){  
	            //ת���ɶಿ��request    
	            MultipartHttpServletRequest multiRequest =           
	                    multipartResolver.resolveMultipart(request);  
	            //ȡ��request�е������ļ���  
//	            Iterator<String> iter = multiRequest.getFileNames();
	            
	            MultiValueMap<String, MultipartFile> iter = multiRequest.getMultiFileMap();
	            System.out.println("size = "+iter.size());
	            for (Map.Entry<String, List<MultipartFile>> entry : iter.entrySet()) {
	                //ȡ���ϴ��ļ�
	                MultipartFile file = multiRequest.getFile(entry.getKey());
	                if(file != null){  
	                    //ȡ�õ�ǰ�ϴ��ļ����ļ�����  
	                    String myFileName = file.getOriginalFilename();  
	                    //������Ʋ�Ϊ����,˵�����ļ����ڣ�����˵�����ļ�������  
	                    if(myFileName.trim() !=""){  
	                        //�������ϴ�����ļ���  
	                        String fileName = file.getOriginalFilename();  
	                        fileName = System.currentTimeMillis()+fileName;
	                        //�����ϴ�·��  
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
