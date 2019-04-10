package com.kykj.tesla.member.service.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("支付宝会员登录接口模板")
public class MemberAlipayLoginRequest {

    @ApiModelProperty(value = "authCode",required = true)
    private String authCode;

    @ApiModelProperty(value = "appId",required = true)
    private String appId;

    @ApiModelProperty(value = "小程序授权码")
    private String app_auth_token;

    @ApiModelProperty(value = "授权范围 主动授权（弹框）：auth_user，静默授权（不弹框）：auth_base")
    private String scopes;

}
