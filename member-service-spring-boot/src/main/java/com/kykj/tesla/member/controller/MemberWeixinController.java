package com.kykj.tesla.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kykj.tesla.member.Service.MemberWeixinService;
import com.kykj.tesla.member.common.JacksonUtils;
import com.kykj.tesla.member.service.MemberWeixinInterface;
import com.kykj.tesla.member.service.model.MemberWeixinModel;
import com.kykj.tesla.member.service.request.MemberWeixinGetUnionIdRequest;
import com.kykj.tesla.member.service.request.MemberWeixinLoginRequest;
import com.kykj.tesla.member.service.request.MemberWeixinSaveRequest;
import com.kykj.tesla.member.service.response.MemberWeixinGetResponse;
import com.kykj.tesla.member.service.response.MemberWeixinLoginResponse;
import com.kykj.tesla.platform.service.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MemberWeixinController implements MemberWeixinInterface {

    @Autowired
    private MemberWeixinService memberWeixinService;

    @Override
    public MemberWeixinLoginResponse login(@RequestBody MemberWeixinLoginRequest request) {
        log.info("微信会员登录req "+ JacksonUtils.toJson(new ObjectMapper(),request));
        MemberWeixinLoginResponse response = new MemberWeixinLoginResponse();
        if(request.getAppId() == null){
            response.resetError("微信小程序appId不能为空！","6004005");
            return response;
        }
        if(request.getCode() == null){
            response.resetError("微信登录code不能为空！","6004006");
            return response;
        }
        MemberWeixinModel memberWeixinModel = memberWeixinService.login(request);
        response.setMemberWeixin(memberWeixinModel);
        log.info("微信会员登录res "+ JacksonUtils.toJson(new ObjectMapper(),response));
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
    public ResultResponse save(@RequestBody MemberWeixinSaveRequest request) {
        log.info("微信会员信息保存req "+ JacksonUtils.toJson(new ObjectMapper(),request));
        ResultResponse response = new ResultResponse();
        boolean result = memberWeixinService.save(request.getMemberWeixin());
        response.setSuccess(result);
        log.info("微信会员信息保存res "+ JacksonUtils.toJson(new ObjectMapper(),response));
        return response;
    }

    @Override
    public MemberWeixinGetResponse get(@RequestParam("memberId") Long memberId) {
        log.info("微信会员信息获取req  memberId="+ memberId);
        MemberWeixinGetResponse response = new MemberWeixinGetResponse();
        if(memberId == null){
            response.resetError("会员id不能为空！","6004004");
            return response;
        }
        MemberWeixinModel model = memberWeixinService.get(memberId);
        if(model == null ){
            response.resetError("未查询到记录","6004005");
            return response;
        }else{
            response.setMemberWeixin(model);
        }
        log.info("微信会员信息获取res  "+ JacksonUtils.toJson(new ObjectMapper(),response));
        return response;
    }

    @Override
    public MemberWeixinGetResponse getUnionId(@RequestBody MemberWeixinGetUnionIdRequest request) {
        log.info("微信完整用户信息解密req  "+ JacksonUtils.toJson(new ObjectMapper(),request));
        MemberWeixinGetResponse response = new MemberWeixinGetResponse();
        MemberWeixinModel model = memberWeixinService.getUnionId(request);
        if(model == null ){
            response.resetError("未查询到记录","6004005");
            return response;
        }else{
            response.setMemberWeixin(model);
        }
        log.info("微信完整用户信息解密res "+ JacksonUtils.toJson(new ObjectMapper(),response));
        return response;
    }
}
