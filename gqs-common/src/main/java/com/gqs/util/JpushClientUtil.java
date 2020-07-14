package com.gqs.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 极光推送
 *
 * @author 郭乔森
 * @create 2020-07-14 13:44
 */
public class JpushClientUtil {

    private static final Logger log = LoggerFactory.getLogger(JpushClientUtil.class);
    private final static String appKey = "ff48606f1d0570f8228a7ec5";
    private final static String masterSecret = "d488362a37352ad6f3d4cc86";
    private static JPushClient jPushClient = new JPushClient(masterSecret, appKey);

    /**
     * 发送给所有安卓用户
     *
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendToAllAndroid(String notification_title, String msg_title, String msg_content, String extrasparam, String cid) {
        int result = 0;
        try {
            PushPayload pushPayload = JpushClientUtil.buildPushObject_android_all_alertWithTitle(notification_title, msg_title, msg_content, extrasparam, cid);
            System.out.println("推送参数:" + pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println("推送结果:" + pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    /**
     * 生产发送条件
     *
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @param cid
     * @return
     */
    private static PushPayload buildPushObject_android_all_alertWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam, String cid) {
        log.info("构建推送参数,如推荐平台,接收对象");
        //具体参看接口文档
        //https://docs.jiguang.cn/jpush/server/push/rest_api_v3_push/
        List list = new ArrayList();
        // Audience.tag(list);
        //  Audience audience = Audience.alias("112201","112202");//别名推送
        // Audience audience = Audience.tag("tag1");
        //Audience audience = Audience.all();
        // Audience audience = Audience.registrationId("112201", "112202");
        Audience audience = Audience.segment("c8f2fff736");
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                //设置发送消息id
                //  .setCid(cid)
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(audience)
                // .setAudience(Audience.tag(list))
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(notification_title)
                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("androidNotification extras key", extrasparam)
                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra("message extras key", extrasparam)
                        .build())

                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    /**
     * 发送测试
     *
     * @param args
     */
    public static void main(String[] args) {
        String num = "-0405-别名";
        String notification_title = "推送测试-通知标题" + num;
        String msg_title = "推送测试-内容标题" + num;
        String msg_content = "我是内容" + num;
        String extrasparam = "我是扩展的json" + num;
        String cid = String.valueOf(System.currentTimeMillis());
        int i = JpushClientUtil.sendToAllAndroid(notification_title, msg_title, msg_content, extrasparam, cid);
        System.out.println("i=" + i);
    }

}
