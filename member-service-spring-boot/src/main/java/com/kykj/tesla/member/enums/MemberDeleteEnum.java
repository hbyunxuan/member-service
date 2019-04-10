package com.kykj.tesla.member.enums;

public enum MemberDeleteEnum {

    ACTIIVE("0", "启用"), DELETE("1", "禁用");

    String code;
    String massage;

    MemberDeleteEnum(String code, String massage) {
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
