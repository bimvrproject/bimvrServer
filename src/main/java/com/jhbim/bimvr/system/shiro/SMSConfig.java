package com.jhbim.bimvr.system.shiro;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

/**
 * 腾讯云短信验证
 */
public class SMSConfig {
    //AppID
    private static final int AppID  = 1400251974;
    //AppKey
    private static final String AppKey  = "49edd61c9bda5a243f3a733bd11a3e87";
    //公司签名ID
    private static final String smsSign = "菁合科技";
    //短信模板ID
    private static final int templateId = 426565;

    public static SmsSingleSenderResult send(String phone, String code){
        //第一个参数验证码，第二个参数1分钟
        String[] params = {code,"1"};
        //调用腾讯云sdk，发送短信
        SmsSingleSender ssender = new SmsSingleSender(AppID , AppKey );
        SmsSingleSenderResult result = null;
        try{
            result = ssender.sendWithParam("86", phone, SMSConfig.templateId, params, smsSign, "", "");
        }catch (HTTPException e){
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
        return result;
    }
}
