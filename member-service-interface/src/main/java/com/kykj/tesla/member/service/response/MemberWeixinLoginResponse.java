package com.kykj.tesla.member.service.response;

import com.kykj.tesla.member.service.model.MemberWeixinModel;
import com.kykj.tesla.platform.service.response.ResultResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("微信小程序登陆响应")
public class MemberWeixinLoginResponse extends ResultResponse {

    @ApiModelProperty("微信用户信息")
   private MemberWeixinModel memberWeixin;

}
