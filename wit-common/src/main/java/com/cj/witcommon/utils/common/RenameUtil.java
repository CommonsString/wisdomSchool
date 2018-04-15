package com.cj.witcommon.utils.common;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Random;

public class RenameUtil {

	
	/**
     * 重命名文件
     * @param multipartFile
     * @return
     */
    public static String rename(MultipartFile multipartFile) {
        String body="";  
        String ext="";  
        Date date = new Date();  
        Random random = new Random();          
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
        int pot=multipartFile.getOriginalFilename().lastIndexOf(".");         
        if(pot!=-1){  
            body= date.getTime() +"";  
            ext=multipartFile.getOriginalFilename().substring(pot);  
        }else{  
            body=(new Date()).getTime()+"";  
            ext="";  
        }  
        String newName=body+rannum+ext;    
        return newName;  
    
     } 
    
    public static String getRandomName(){
    	
    	 String body=(new Date()).getTime()+""; 
         Random random = new Random();          
         int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数             
         String newName=body+rannum;
         return newName;
    }
}
