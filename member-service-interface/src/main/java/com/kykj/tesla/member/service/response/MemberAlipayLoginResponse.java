package com.kykj.tesla.member.service.response;

import com.kykj.tesla.member.service.model.MemberAlipayModel;
import com.kykj.tesla.platform.service.response.ResultResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("支付宝会员登录响应")
public class MemberAlipayLoginResponse extends ResultResponse {

    @ApiModelProperty("阿里云会员信息")
    private MemberAlipayModel memberAlipayModel;

}
