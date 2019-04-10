package com.kykj.tesla.member.service.request;

import com.kykj.tesla.member.service.model.MemberWeixinModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("微信会员信息保存请求")
public class MemberWeixinSaveRequest {

    @ApiModelProperty("微信会员信息")
    private MemberWeixinModel memberWeixin;

}
