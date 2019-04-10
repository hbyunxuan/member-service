package com.kykj.tesla.member.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("微信会员信息模板")
public class MemberWeixinModel {

    @ApiModelProperty("用户唯一标识的openid")
    private String openId;

    @ApiModelProperty("会员Id")
    private Long memberId;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户头像图片的 URL")
    private String avatarUrl;

    @ApiModelProperty("用户性别 0未知，1 男性，2女性")
    private Byte gender;

    @ApiModelProperty("用户所在国家")
    private String country;

    @ApiModelProperty("用户所在城市")
    private String city;

    @ApiModelProperty("用户平台唯一id")
    private String unionId;

}
