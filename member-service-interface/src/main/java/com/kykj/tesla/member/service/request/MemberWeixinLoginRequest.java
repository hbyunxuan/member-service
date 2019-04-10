package com.kykj.tesla.member.service.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("微信会员登录请求参数")
public class MemberWeixinLoginRequest {

    @ApiModelProperty(value = "微信用户临时登录凭证 一次有效性",required = true)
    private String code;

    @ApiModelProperty(value = "小程序appId",required = true)
    private String appId;

}
