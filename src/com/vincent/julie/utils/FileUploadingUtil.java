package com.vincent.julie.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.multipart.MultipartFile;

/**  
 * @Title:  FileUploadingUtil.java   
 * @Package com.vincent.julie.utils   
 * @Description:    TODO(��һ�仰�������ļ���ʲô)   
 * @author: Vinent QQ:1032006226
 * @date:   2018��4��6�� ����11:58:00   
 * @version V1.0 
 * @Copyright: 2018 
 * ע�⣺�����ݽ���������д��
 */

public class FileUploadingUtil {

	/** 
     * �������ϵı���·������ʹ�õ��ϴ����ܵ�Controller�ж�����и�ֵ 
     */  
    public static String FILEDIR = null;  
  
    /** 
     * �ϴ�����ļ��������ļ����ƺͷ������洢·���б� 
     *  
     * @param files 
     * @return 
     * @throws IOException 
     */  
    public static Map<String, String> upload(Map<String, MultipartFile> files) throws IOException {  
        File file = new File(FILEDIR);  
        if (!file.exists()) {  
            file.mkdir();  
        }  
  
        Map<String, String> result = new HashMap<String, String>();  
        Iterator<Entry<String, MultipartFile>> iter = files.entrySet().iterator();  
        while (iter.hasNext()) {  
            MultipartFile aFile = iter.next().getValue();  
            if (aFile.getSize() != 0 && !"".equals(aFile.getName())) {  
                result.put(aFile.getOriginalFilename(), uploadFile(aFile));  
            }  
        }  
        return result;  
    }  
  
    /** 
     * �ϴ������ļ������������ڷ������еĴ洢·�� 
     *  
     * @param aFile 
     * @return 
     * @throws FileNotFoundException 
     * @throws IOException 
     */  
    private static String uploadFile(MultipartFile aFile) throws IOException {  
        String filePath = initFilePath(aFile.getOriginalFilename());  
        try {  
            write(aFile.getInputStream(), new FileOutputStream(filePath));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
        return filePath;  
    }  
  
    /** 
     * д������ 
     *  
     * @param in 
     * @param out 
     * @throws IOException 
     */  
    private static void write(InputStream in, OutputStream out) throws IOException {  
        try {  
            byte[] buffer = new byte[1024];  
            int bytesRead = -1;  
            while ((bytesRead = in.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesRead);  
            }  
            out.flush();  
        } finally {  
            try {  
                in.close();  
                out.close();  
            } catch (IOException ex) {  
            }  
        }  
    }  
  
    /** 
     * ����������Ŀ¼���оٳ�Ŀ¼�е������ļ�������Ŀ¼�� 
     * @return 
     */  
    public static Map<String, String> getFileMap() {  
        Map<String, String> map = new HashMap<String, String>();  
        File[] files = new File(FileUploadingUtil.FILEDIR).listFiles();  
        if (files != null) {  
            for (File file : files) {  
                if (file.isDirectory()) {  
                    File[] files2 = file.listFiles();  
                    if (files2 != null) {  
                        for (File file2 : files2) {  
                            String name = file2.getName();  
                            map.put(file2.getParentFile().getName() + "/" + name,  
                                    name.substring(name.lastIndexOf("_") + 1));  
                        }  
                    }  
                }  
            }  
        }  
        return map;  
    }  
  
    /** 
     * �����ļ��洢·����Ϊ��ֹ�����ļ������ǣ����ļ������������������ 
     * @param name 
     * @return 
     */  
    private static String initFilePath(String name) {  
        String dir = getFileDir(name) + "";  
        File file = new File(FILEDIR + dir);  
        if (!file.exists()) {  
            file.mkdir();  
        }  
        Long num = new Date().getTime();  
        Double d = Math.random() * num;  
        return (file.getPath() + "/" + num + d.longValue() + "_" + name).replaceAll(" ", "-");  
    }  
  
    /** 
     *  
     * @param name 
     * @return 
     */  
    private static int getFileDir(String name) {  
        return name.hashCode() & 0xf;  
    }  
	
	
}
