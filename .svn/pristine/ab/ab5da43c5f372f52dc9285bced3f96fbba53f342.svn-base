package com.cj.witcommon.utils.common;


import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by onglchen on 5/24/15.
 */
public class FileUtil {
    private static String picture_fomate = "jpeg;gif;png;jpg;bmp";
    private static String video_fomate = "mp4;avi;flv;rmvb;3gp;rm;wma;mid;flash";
    public static int FOMATE_PICTURE = 2, FOMATE_VIDEO = 4, FOMATE_EERO = 6;

    //获取后缀
    public static String getExtensions(String fileName) {
        String extention = "";
        int i = fileName.lastIndexOf(".");
        if (i > -1 && i < fileName.length()) {
            extention = fileName.substring(i + 1); // --扩展名
        }
        return extention;
    }

    public static String getNameWithoutExtensions(String fileName) {
        String name = "";
        int i = fileName.lastIndexOf(".");
        if (i > -1 && i < fileName.length()) {
            name = fileName.substring(0, i); // --扩展名
        }
        return name;
    }

    public static boolean isPicture(String fileName) {
        boolean status = false;
        String extention = getExtensions(fileName);
        String[] fomate = picture_fomate.split(";");
        for (int i = 0; i < fomate.length; i++) {
            if (extention.compareToIgnoreCase(fomate[i]) == 0) {
                status = true;
                return status;
            }
        }
        return status;
    }

    public static boolean isVideo(String fileName) {
        boolean status = false;
        String extention = getExtensions(fileName);
        String[] fomate = video_fomate.split(";");
        for (int i = 0; i < fomate.length; i++) {
            if (extention.compareToIgnoreCase(fomate[i]) == 0) {
                status = true;
                return status;
            }
        }
        return status;
    }

    public static int judgeFileType(String fileName) {
        if (isPicture(fileName)) {
            return FOMATE_PICTURE;
        } else if (isVideo(fileName)) {
            return FOMATE_VIDEO;
        } else {
            return FOMATE_EERO;
        }
    }

    public static String getBackPath(String filePath) {
        String temp[] = filePath.replaceAll("\\\\", "/").split("/");
        int length = temp.length;
        String result[] = new String[length];
        String restult2 = "";
        for (int i = 0; i < length - 1; i++) {
            result[i] = temp[i];
            restult2 += "/" + temp[i];
        }
        System.out.println(restult2);
        return restult2;
    }

    public static String getFileName(String fileName) {
        String[] result = fileName.split("\\.");
        String result2 = result[0];
        return result2;
    }

    public static void Copy(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
            oldfile.delete();
        } catch (Exception e) {
            System.out.println("error  ");
            e.printStackTrace();
        }
    }

    public static boolean move(String srcFile, String destPath) {
        // File (or directory) to be moved
        File file = new File(srcFile);

        // Destination directory
        File dir = new File(destPath);

        // Move file to new directory
        boolean success = file.renameTo(new File(dir, file.getName()));

        return success;
    }

    public static void fileMove(String from, String to) throws Exception {// 移动指定文件夹内的全部文件
        try {
            File dir = new File(from);
            File[] files = dir.listFiles();// 将文件或文件夹放入文件集
            if (files == null)// 判断文件集是否为空
                return;
            File moveDir = new File(to);// 创建目标目录
            if (!moveDir.exists()) {// 判断目标目录是否存在
                moveDir.mkdirs();// 不存在则创建
            }
            for (int i = 0; i < files.length; i++) {// 遍历文件集
                if (files[i].isDirectory()) {// 如果是文件夹或目录,则递归调用fileMove方法，直到获得目录下的文件
                    fileMove(files[i].getPath(), to + "\\" + files[i].getName());// 递归移动文件
                    files[i].delete();// 删除文件所在原目录
                }
                File moveFile = new File(moveDir.getPath() + "\\"// 将文件目录放入移动后的目录
                        + files[i].getName());
                if (moveFile.exists()) {// 目标文件夹下存在的话，删除
                    moveFile.delete();
                }
                files[i].renameTo(moveFile);// 移动文件
                System.out.println(files[i] + " 移动成功");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static int renameFile(String path, String oldname, String newname) {
        if (!oldname.equals(newname)) {
            File oldFile = new File(path + "/" + oldname);
            File newFile = new File(path + "/" + newname);
            if (newFile.exists()) {
                return Status.EXISTS;
            } else {
                oldFile.renameTo(newFile);
                return Status.SUCCESS;
            }
        }
        return Status.ERROR;
    }

    public static String FormetFileSize(int fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static void deleteFile(String fileUrl) {
        File file = new File(fileUrl);
        if (file.exists() && file.isFile() || file.isDirectory()) {
            file.delete();
        }
    }

    public static String rename(String fileName) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(
                "yyyyMMddHHmmss");
        String extention = "";
        int i = fileName.lastIndexOf(".");
        if (i > -1 && i < fileName.length()) {
            extention = fileName.substring(i + 1); // --扩展名
        }
        return f.format(c.getTime()) + "." + extention;
    }


    // 删除指定文件夹下所有文件
    // param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    // 删除文件夹
    // param folderPath 文件夹完整绝对路径

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFileNameFromPath(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
    }

    //获得指定文件的byte数组
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    //根据byte数组，生成文件
    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void deleteFileByDay(String directory, int beforeDay) {
        File file = new File(directory);
        Date now = new Date();
        for (File f : file.listFiles()) {//把大于1天的文件删掉
            if (now.getTime() - f.lastModified() > beforeDay * 24 * 3600 * 1000) {
                f.delete();
                ActionUtil.fileNameMap.remove(f.getName());
            }
        }
    }

    /**
     * 文件上传
     *
     * path=${web.upload-path}
     * 返回文件存储地址
     *
     * @throws Exception
     */
    public static String uploadFile(String path, MultipartFile file) throws Exception {


        // 获取上传的文件的名称
        String filename = file.getOriginalFilename();
        //修改文件名称 uuid
        String fileUUIDname = UUID.randomUUID().toString();
        //获取后缀
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        //修改后完整的文件名称
        String NewFileName = fileUUIDname + "." + prefix;
        //完整的路径
        String completepath = path  + NewFileName;

        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(completepath));


//        byte[] bs = file.getBytes();
//
//        FileUtils.writeByteArrayToFile(new File(completepath), bs);

        return completepath;
    }

    /**
     * 图片上传
     * Base64
     */
    public static String uploadImgBase64(String base64Data,String path,HttpServletRequest request){
        try{
            String dataPrix = "";
            String data = "";
            if(base64Data == null || "".equals(base64Data)){
                throw new Exception("上传失败，上传图片数据为空");
            }else{
                String [] d = base64Data.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                    throw new Exception("上传失败，数据不合法");
                }
            }
            String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                throw new Exception("上传图片格式不合法");
            }
            String tempFileName = UUID.randomUUID().toString() + suffix;

            //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
            try{
                //使用apache提供的工具类操作流

                System.out.println(request.getServletContext().getRealPath("/upload"));

                FileUtils.writeByteArrayToFile(new File(path, tempFileName), bs);
            }catch(Exception ee){
                throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
            }
            return path+"/"+tempFileName;
        }catch (Exception e) {
            return "error";
        }
    }

    /**
     * 文件下载
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String path,String fileName) throws IOException {
//        String new_filename;
//        String rtn;
//
//        String userAgent = request.getHeader("USER-AGENT");
//
//        if(userAgent.contains("Firefox")){
//            //是火狐浏览器，使用BASE64编码
//            fileName = "=?utf-8?b?"+new BASE64Encoder().encode(fileName.getBytes("utf-8"))+"?=";
//        }else{
//            //给文件名进行URL编码
//            //URLEncoder.encode()需要两个参数，第一个参数时要编码的字符串，第二个是编码所采用的字符集
//            fileName = URLEncoder.encode(fileName, "utf-8");
//        }

//        new_filename = URLEncoder.encode(fileName, "UTF8");
//// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
//        rtn = "filename=\"" + new_filename + "\"";
//        if (userAgent != null)
//        {
//            userAgent = userAgent.toLowerCase();
//            // IE浏览器，只能采用URLEncoder编码
//            if (userAgent.indexOf("msie") != -1)
//            {
//                rtn = "filename=\"" + new_filename + "\"";
//            }
//            // Opera浏览器只能采用filename*
//            else if (userAgent.indexOf("opera") != -1)
//            {
//                rtn = "filename*=UTF-8''" + new_filename;
//            }
//            // Safari浏览器，只能采用ISO编码的中文输出
//            else if (userAgent.indexOf("safari") != -1 )
//            {
//                rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO8859-1") + "\"";
//            }
//            // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
//            else if (userAgent.indexOf("applewebkit") != -1 )
//            {
//                new_filename = MimeUtility.encodeText(fileName, "UTF8", "B");
//                rtn = "filename=\"" + new_filename + "\"";
//            }
//            // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
//            else if (userAgent.indexOf("mozilla") != -1)
//            {
//                rtn = "filename*=UTF-8''" + new_filename;
//            }
//        }
//        // 针对IE或者以IE为内核的浏览器：
//        if (fileName.contains("MSIE") || fileName.contains("Trident")) {
//            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
//        } else {
//            // 非IE浏览器的处理：
//            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//        }
//        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//        } else {
//            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
//        }
//        String transcoding = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
        String downloadName = URLEncoder.encode(fileName, "UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;fileName=" + downloadName);

        InputStream is = null;
        OutputStream os = null;
        try {
//            String path = request.getServletContext().getContextPath();
            is = new FileInputStream(new File(path + fileName));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            os.close();
            is.close();
        }
    }


}
