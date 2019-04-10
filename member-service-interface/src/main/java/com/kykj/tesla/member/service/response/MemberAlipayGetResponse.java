package com.kykj.tesla.member.service.response;

import com.kykj.tesla.member.service.model.MemberAlipayModel;
import com.kykj.tesla.platform.service.response.ResultResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("支付宝会员信息获取响应")
public class MemberAlipayGetResponse extends ResultResponse {

    @ApiModelProperty("支付宝会员信息模板")
    private MemberAlipayModel memberAliypay;

}
