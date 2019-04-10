package com.kykj.tesla.member.service.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel()
public class MemberWeixinGetUnionIdRequest {

    @ApiModelProperty(value = "会员id",required = true)
    private Long memberId;

    @ApiModelProperty(value = "完整用户信息的加密数据",required = true)
    private String encryptedData;

    @ApiModelProperty(value = "微信加密算法的初始向量",required = true)
    private String iv;


}
