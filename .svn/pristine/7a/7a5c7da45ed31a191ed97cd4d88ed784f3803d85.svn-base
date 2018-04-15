package com.cj.witcommon.utils.rsa;

import com.cj.witcommon.utils.aes.AESUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by XD on 2017/12/12.
 */
@Controller
@ApiIgnore
public class TestRSAController {


    /**
     * 获取系数和指数
     * @return
     * @throws Exception
     */
    @RequestMapping("/rsa/keyPair")
    @ResponseBody
    public PublicKeyMap keyPair() throws Exception{
        PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap();
        return publicKeyMap;
    }

    //测试RSA加密
    @RequestMapping("/rsa/decryption")
    public ModelAndView decryption(String userName, String pwd, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println(userName);
        System.out.println(pwd);

        userName  = RSAUtils.decryptStringByJs(userName);
        pwd  = RSAUtils.decryptStringByJs(pwd);

        userName = URLDecoder.decode(userName,"UTF-8");

        System.out.println(userName);
        System.out.println(pwd);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("decryption");
        mav.addObject("userName", userName);
        mav.addObject("pwd", pwd);

        return mav;
    }

    //测试AES+RSA加密
    @RequestMapping("/rsa/decryption2")
    @ResponseBody
    public String decryption2(@RequestBody Map<String,String> map) throws Exception {

        System.out.println("===============测试AES+RSA加密================");

        String miwen = map.get("miwen");
        String miyao = map.get("miyao");

        System.out.println("AES加密后的密文==>"+miwen);
        System.out.println("RSA加密后的AES秘钥==>"+miyao);

        String miyao2 = RSAUtils.decryptStringByJs(miyao);
        System.out.println("AES秘钥==>"+miyao2);

        String miwen2 = AESUtils.aesDecrypt(miwen,miyao2);
        System.out.println("解密后的明文==>"+miwen2);

        String msg = "AES测试成功";

        return AESUtils.aesEncrypt(msg,miyao2);

    }


}
