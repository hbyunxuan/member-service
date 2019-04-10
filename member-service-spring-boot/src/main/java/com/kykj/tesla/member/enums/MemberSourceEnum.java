package com.kykj.tesla.member.enums;

public enum MemberSourceEnum {

    ALIPAY("alipay", "支付宝"), WEIXIN("weixin", "微信");

    String code;
    String massage;

    MemberSourceEnum(String code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public String getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }

}
