package com.cj.witcommon.utils.common;


/**
 * Created by onglchen
 */
public class PayUtils {

    /**
     * pingpp 管理平台对应的 API key
     */
    public static String apiKey = "YOUR-KEY";
    /**
     * pingpp 管理平台对应的应用 ID
     */
    public static String appId = "YOUR-APPID";

    /**
     * 创建 Charge
     *
     * 创建 Charge 用户需要组装一个 map 对象作为参数传递给 Charge.create();
     * map 里面参数的具体说明请参考：https://pingxx.com/document/api#api-c-new
     * @return
     */
   /* public Charge charge() {
        Charge charge = null;
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", 100);
        chargeMap.put("currency", "cny");
        chargeMap.put("subject", "Your Subject");
        chargeMap.put("body", "Your Body");
        chargeMap.put("order_no", "123456789");
        chargeMap.put("channel", "alipay");
        chargeMap.put("client_ip", "127.0.0.1");
        Map<String, String> app = new HashMap<String, String>();
        app.put("id",appId);
        chargeMap.put("app", app);
        try {
            //发起交易请求
            charge = Charge.create(chargeMap);
            System.out.println(charge);
        } catch (PingppException e) {
            e.printStackTrace();
        }
        return charge;
    }*/

    /**
     * 查询 Charge
     *
     * 该接口根据 charge Id 查询对应的 charge 。
     * 参考文档：https://pingxx.com/document/api#api-c-inquiry
     *
     * 该接口可以传递一个 expand ， 返回的 charge 中的 app 会变成 app 对象。
     * 参考文档： https://pingxx.com/document/api#api-expanding
     * @param id
     */
    /*public void retrieve(String id) {
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            List<String> expande = new ArrayList<String>();
            expande.add("app");
            param.put("expand", expande);
            //Charge charge = Charge.retrieve(id);
            //Expand app
            Charge charge = Charge.retrieve(id, param);
            if (charge.getApp() instanceof App) {
                //App app = (App) charge.getApp();
                // System.out.println("App Object ,appId = " + app.getId());
            } else {
                // System.out.println("String ,appId = " + charge.getApp());
            }

            System.out.println(charge);

        } catch (PingppException e) {
            e.printStackTrace();
        }
    }*/
}
