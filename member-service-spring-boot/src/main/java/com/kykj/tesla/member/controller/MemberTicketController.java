package com.kykj.tesla.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kykj.tesla.member.Service.MemberTicketService;
import com.kykj.tesla.member.common.JacksonUtils;
import com.kykj.tesla.member.service.MemberTicketBuyerInterface;
import com.kykj.tesla.member.service.model.MemberTicketBuyerModel;
import com.kykj.tesla.member.service.request.MemberTicketBuyerSaveRequest;
import com.kykj.tesla.member.service.response.MemberTicketBuyerGetResponse;
import com.kykj.tesla.platform.service.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class MemberTicketController implements MemberTicketBuyerInterface {

    @Autowired
    private MemberTicketService memberTicketService;

    @Override
    public ResultResponse save(@RequestBody MemberTicketBuyerSaveRequest memberTicketSaveRequest) {
        log.info("购票人信息保存 req "+ JacksonUtils.toJson(new ObjectMapper(),memberTicketSaveRequest));
        ResultResponse response = new ResultResponse();
        if(memberTicketSaveRequest.getMemberId() == null){
            response.resetError("会员id不能为空！","6004004");
            return response;
        }
        boolean result = memberTicketService.save(memberTicketSaveRequest);
        if(!result){
            response.resetError("系统保存出错！","6004005");
        }
        log.info("购票人信息保存响应 ："+response);
        return response;
    }

    @Override
    public MemberTicketBuyerGetResponse get(@RequestParam("memberId") Long memberId) {
        MemberTicketBuyerGetResponse response = new MemberTicketBuyerGetResponse();
        if(memberId == null){
            response.resetError("会员id不能为空！","6004004");
            return response;
        }
        List<MemberTicketBuyerModel> list = memberTicketService.getMemberTicketBuyerListByMemberId(memberId);
        response.setMemberTicketList(list);
        return response;
    }

}
