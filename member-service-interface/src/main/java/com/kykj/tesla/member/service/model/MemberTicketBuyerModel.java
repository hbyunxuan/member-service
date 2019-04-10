package com.kykj.tesla.member.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会员购票人信息模板")
public class MemberTicketBuyerModel {

    @ApiModelProperty("购票人身份证号")
    private String idcard;

    @ApiModelProperty("购票人姓名")
    private String name;

    @ApiModelProperty("购票人联系电话")
    private String phone;

}
