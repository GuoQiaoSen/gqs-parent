package com.gqs.util;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;

/**
 * @Author Honey
 * @Date 2020/2/4 17:36
 * @Description 短信发送工具类
 **/
public class SMSUtil {

    /**
     * @param serverIP     短信服务地址
     * @param port         短信服务端口
     * @param accountSid   短信服务accountSid
     * @param accountToken 主帐号TOKEN
     * @param AppId        短信服务应用ID
     * @param phone        短信服务模板
     * @param templateId   短信服务模板
     * @param rep          模板需要替换的字段
     * @return
     */
    public final static HashMap sendSMS(String serverIP, String port, String accountSid,
                                        String accountToken, String AppId, String phone, String templateId, String[] rep) {
        HashMap<String, Object> result = null;
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
        // 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
        restAPI.init(serverIP, port);
        // 初始化主帐号和主帐号TOKEN
        restAPI.setAccount(accountSid, accountToken);
        // 初始化应用ID
        restAPI.setAppId(AppId);
        result = restAPI.sendTemplateSMS(phone, templateId, rep);
        return result;
    }
}
