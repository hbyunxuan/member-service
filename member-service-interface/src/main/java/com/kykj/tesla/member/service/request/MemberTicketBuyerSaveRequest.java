package com.kykj.tesla.member.service.request;

import com.kykj.tesla.member.service.model.MemberTicketBuyerModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("会员保存购票人信息请求模板")
public class MemberTicketBuyerSaveRequest {

    @ApiModelProperty("会员Id")
    private Long memberId;

    @ApiModelProperty("购票人信息模板")
    private MemberTicketBuyerModel memberTicket;

}
