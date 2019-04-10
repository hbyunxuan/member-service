package com.kykj.tesla.member.service;

import com.kykj.tesla.member.service.request.MemberTicketBuyerSaveRequest;
import com.kykj.tesla.member.service.response.MemberTicketBuyerGetResponse;
import com.kykj.tesla.platform.service.response.ResultResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 会员购票人信息接口
 */
public interface MemberTicketBuyerInterface {

    /*
     * 会员购票人信息保存
     * */
    @ApiOperation("会员购票人信息保存")
    @RequestMapping(value = "/api/member/ticket_buyer/save", method = RequestMethod.POST)
    ResultResponse save(@RequestBody MemberTicketBuyerSaveRequest memberTicketSaveRequest);

    /*
     * 会员购票人信息获取
     * */
    @ApiOperation("会员购票人信息获取")
    @RequestMapping(value = "/api/member/ticket_buyer/get", method = RequestMethod.POST)
    MemberTicketBuyerGetResponse get(@RequestParam("memberId") Long memberId);

}
