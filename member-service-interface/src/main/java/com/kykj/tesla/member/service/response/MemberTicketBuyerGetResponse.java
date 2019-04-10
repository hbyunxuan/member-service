package com.kykj.tesla.member.service.response;

import com.kykj.tesla.member.service.model.MemberTicketBuyerModel;
import com.kykj.tesla.platform.service.response.ResultResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class MemberTicketBuyerGetResponse extends ResultResponse {

    @ApiModelProperty("会员购票人信息集合")
    private List<MemberTicketBuyerModel> memberTicketList;

}
