package com.kykj.tesla.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kykj.tesla.member.Service.MemberAlipayService;
import com.kykj.tesla.member.common.JacksonUtils;
import com.kykj.tesla.member.service.MemberAlipayInterface;
import com.kykj.tesla.member.service.model.MemberAlipayModel;
import com.kykj.tesla.member.service.request.MemberAlipayLoginRequest;
import com.kykj.tesla.member.service.response.MemberAlipayGetResponse;
import com.kykj.tesla.member.service.response.MemberAlipayLoginResponse;
import com.kykj.tesla.platform.service.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MemberAlipayController implements MemberAlipayInterface {

    @Autowired
    private MemberAlipayService memberAlipayService;

    @Override
    public MemberAlipayLoginResponse login(@RequestBody MemberAlipayLoginRequest memberAlipayLoginRequest) {
        log.info("支付宝用户登录req "+ JacksonUtils.toJson(new ObjectMapper(),memberAlipayLoginRequest));
        MemberAlipayLoginResponse response = new MemberAlipayLoginResponse();
        if(memberAlipayLoginRequest.getAppId() == null){
            response.resetError("支付宝小程序appid不能为空！","6004001");
            return response;
        }
        if(memberAlipayLoginRequest.getAuthCode() == null){
            response.resetError("支付宝用户登录code不能为空！","6004002");
            return response;
        }
        MemberAlipayModel memberAlipayModel = memberAlipayService.login(memberAlipayLoginRequest);
        response.setMemberAlipayModel(memberAlipayModel);
        log.info("支付宝用户登录res "+ JacksonUtils.toJson(new ObjectMapper(),response));
        return response;
    }

    @Override
    public ResultResponse logout(@RequestParam("memberId") Long memberId) {
        ResultResponse response = new ResultResponse();
        if(memberId == null){
            response.resetError("会员id不能为空！","6004004");
            return response;
        }
        return response;
    }

    @Override
    public MemberAlipayGetResponse get(@RequestParam("memberId") Long memberId) {
        log.info("获取支付宝用户信息req  memberId =  "+ memberId);
        MemberAlipayGetResponse response = new MemberAlipayGetResponse();
        if(memberId == null){
            response.resetError("会员id不能为空！","6004004");
            return response;
        }
        MemberAlipayModel memberAlipayModel = memberAlipayService.getMemberAlipayByMemberId(memberId);
        response.setMemberAliypay(memberAlipayModel);
        log.info("获取支付宝用户信息res"+ JacksonUtils.toJson(new ObjectMapper(),response));
        return response;
    }

}
