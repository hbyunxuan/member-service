package com.kykj.tesla.member.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("支付宝会员信息模板")
public class MemberAlipayModel {

    @ApiModelProperty("支付宝用户唯一标号")
    private String userId;

    @ApiModelProperty("会员Id")
    private Long memberId;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("省份")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("用户头像信息")
    private String headimgurl;

    @ApiModelProperty("用户类型")
    private String userType;

    @ApiModelProperty("是否通过实名认证")
    private String userRealFlag;

    @ApiModelProperty("是否通过学生认证")
    private String userStudentFlag;

}
