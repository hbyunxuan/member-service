package com.kykj.tesla.member.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Res {
    public static String ALIPAY_PUBLICKEY;
    public static String ALIPAY_PRIVATEKEY;
    public static String ALIPAY_APPID;
    public static String WEIXIN_APPID;
    public static String YOUCHENG_WEIXIN_APPID;
    public static String YOUCHENG_WEIXIN_APPSECRET;

    public static String YOUCHENG_ALIPAY_PUBLICKEY;
    public static String YOUCHENG_ALIPAY_PRIVATEKEY;
    public static String YOUCHENG_ALIPAY_APPID;

    @Value("${alipay.applet.alipayPublicKey}")
    public void setAlipayPublicKey(String alipayPublicKey){
        ALIPAY_PUBLICKEY = alipayPublicKey;
    }

    @Value("${alipay.applet.appPrivateKey}")
    public void setPrivateKey(String privateKey){
        ALIPAY_PRIVATEKEY = privateKey;
    }

    @Value("${alipay.applet.appid}")
    public void setAppid(String appid){
        ALIPAY_APPID = appid;
    }

    @Value("${weixin.applet.appid}")
    public void setWeiXinAppid(String appid) {
        WEIXIN_APPID = appid;
    }

    @Value("${weixin.applet.youcheng.appid}")
    public void setYouchengWeixinAppid(String youchengWeixinAppid) {
        YOUCHENG_WEIXIN_APPID = youchengWeixinAppid;
    }

    @Value("${weixin.applet.youcheng.appsecret}")
    public void setYouchengWeixinAppsecret(String youchengWeixinAppsecret) {
        YOUCHENG_WEIXIN_APPSECRET = youchengWeixinAppsecret;
    }

    @Value("${alipay.applet.youcheng.alipayPublicKey}")
    public void setYouchengAlipayPublickey(String youchengAlipayPublickey) {
        YOUCHENG_ALIPAY_PUBLICKEY = youchengAlipayPublickey;
    }

    @Value("${alipay.applet.youcheng.appPrivateKey}")
    public void setYouchengAlipayPrivatekey(String youchengAlipayPrivatekey) {
        YOUCHENG_ALIPAY_PRIVATEKEY = youchengAlipayPrivatekey;
    }

    @Value("${alipay.applet.youcheng.appid}")
    public void setYouchengAlipayAppid(String youchengAlipayAppid) {
        YOUCHENG_ALIPAY_APPID = youchengAlipayAppid;
    }
}
