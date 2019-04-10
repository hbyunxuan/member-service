package com.kykj.tesla.member.service.response;

import com.kykj.tesla.member.service.model.MemberWeixinModel;
import com.kykj.tesla.platform.service.response.ResultResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("微信会员信息获取相应")
public class MemberWeixinGetResponse extends ResultResponse {

    @ApiModelProperty("微信会员信息")
    private MemberWeixinModel memberWeixin;

}
