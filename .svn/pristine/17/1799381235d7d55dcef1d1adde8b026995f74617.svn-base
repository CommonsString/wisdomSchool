package com.cj.witcommon.utils.http;

import com.cj.witcommon.entity.ApiResult2;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static com.cj.witcommon.utils.json.JSONUtil.parseObject;

public class APIHttpClient {

    // 接口地址
    private static String apiURL = "http://192.168.3.67:8080/lkgst_manager/order/order";
    private Log logger = LogFactory.getLog(this.getClass());
    private static final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
    private HttpClient httpClient = null;
    private HttpPost httpPost = null;
    private long startTime = 0L;
    private long endTime = 0L;
    private int status = 0;

    /**
     * 接口地址
     *
     * @param url
     */
    public APIHttpClient(String url) {

        if (url != null) {
            this.apiURL = url;
        }
        if (apiURL != null) {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(apiURL);

        }
    }

    /**
     * 发送 get请求
     */
    public static String doGet(String url,String userId) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建参数队列
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("username", "admin"));
//        params.add(new BasicNameValuePair("password", "123456"));

        String str = null;

        try {
            //参数转换为字符串
//            String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
//            String url = "http://localhost:8888/myTest/testHttpClientGet" + "?" + paramsStr;
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            httpget.setHeader("userId",userId);
            System.out.println("请求地址： " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
//                    System.out.println("打印响应内容长度: " + entity.getContentLength());
                    // 打印响应内容
//                    System.out.println("打印响应内容: " + EntityUtils.toString(entity));

                    str = EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return str;
    }


    /**
     * 调用 API
     * @param parameters
     * @return
     */
    public String post(String parameters,Long userId,String token) {
        String body = null;
        logger.info("parameters:" + parameters);

        if (httpPost != null & parameters != null
                && !"".equals(parameters.trim())) {
            try {

                // 建立一个NameValuePair数组，用于存储欲传送的参数
                httpPost.setHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
                System.out.println("userId===>>"+userId);
                System.out.println("token===>>"+token);
                if( userId > 0){
                    httpPost.setHeader("userId",userId.toString());
                    httpPost.setHeader("token",token);
                }
                startTime = System.currentTimeMillis();

                HttpResponse response = httpClient.execute(httpPost);

                endTime = System.currentTimeMillis();
                int statusCode = response.getStatusLine().getStatusCode();

                logger.info("statusCode:" + statusCode);
                logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
                if (statusCode != HttpStatus.SC_OK) {
                    logger.error("Method failed:" + response.getStatusLine());
                    status = 1;
                }

                // Read the response body
                body = EntityUtils.toString(response.getEntity());

            } catch (IOException e) {
                // 网络错误
                status = 3;
            } finally {
                logger.info("调用接口状态：" + status);
            }

        }
        return body;
    }



    public static void main(String[] args) {
        APIHttpClient ac = new APIHttpClient("http://120.78.191.127:9005/api/v1/auth/login");
        JsonArray arry = new JsonArray();
        JsonObject j = new JsonObject();
        j.addProperty("name", "asd");
        j.addProperty("password", "96e79218965eb72c92a549dd5a330112");
        j.addProperty("userkey", "4");
        j.addProperty("stageId", "1");
        arry.add(j);
        String str = ac.post(arry.toString(),0l,"");
        ApiResult2 apiResult2 = parseObject(str, ApiResult2.class);
        System.out.println(apiResult2);
    }

    /**
     * 0.成功 1.执行方法失败 2.协议错误 3.网络错误
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * 文件上传
     * @throws ClientProtocolException
     * @throws IOException
     */

    public static String upload(String url, File file,HttpServletRequest request) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String responseEntityStr = "";
        try {
            HttpPost httppost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
            httppost.setConfig(requestConfig);
            httppost.setHeader("userId","10352");
            httppost.setHeader("token","b68d4f2cd2ef49c4b621dc3ccd84074f");

            FileBody bin = new FileBody(file);
            StringBody comment = new StringBody("This is comment", ContentType.TEXT_PLAIN);

            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).addPart("comment", comment).build();

            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    responseEntityStr = EntityUtils.toString(response.getEntity());
                    System.out.println(responseEntityStr);
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return responseEntityStr;
    }


    /**
     * MultipartFile 转 File
     * @param file
     * @param request
     * @return
     */
    public static File analyzeFile(MultipartFile file, HttpServletRequest request) {
        File tempFile = null;
        if(!file.isEmpty()) {
            String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
            File dir = new File(filePath);
            if(! dir.exists()) {
                dir.mkdir();
            }

            String path = filePath + file.getOriginalFilename();

            //save to the /upload path
            try {
                tempFile = new File(path);
                FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
            }catch (Exception e){

            }
            }

            return tempFile;
        }
}