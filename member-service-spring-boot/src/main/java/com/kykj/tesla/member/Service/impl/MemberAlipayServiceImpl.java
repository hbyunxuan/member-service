package com.kykj.tesla.member.Service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.kykj.tesla.member.Service.MemberAlipayService;
import com.kykj.tesla.member.common.Res;
import com.kykj.tesla.member.entity.Member;
import com.kykj.tesla.member.entity.MemberAlipay;
import com.kykj.tesla.member.entity.MemberAlipayExample;
import com.kykj.tesla.member.enums.MemberDeleteEnum;
import com.kykj.tesla.member.enums.MemberSourceEnum;
import com.kykj.tesla.member.mapper.dao.MemberAlipayMapper;
import com.kykj.tesla.member.mapper.dao.MemberMapper;
import com.kykj.tesla.member.service.model.MemberAlipayModel;
import com.kykj.tesla.member.service.request.MemberAlipayLoginRequest;
import com.kykj.tesla.platform.service.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MemberAlipayServiceImpl implements MemberAlipayService {

    @Autowired
    @SuppressWarnings("all")
    private MemberAlipayMapper memberAlipayMapper;

    @Autowired
    @SuppressWarnings("all")
    private MemberMapper memberMapper;

    public static AlipayClient alipayClient;

    public static AlipayClient youChengAlipayClient;

    static {
        String url = "https://openapi.alipay.com/gateway.do";
        alipayClient = new DefaultAlipayClient(url, Res.ALIPAY_APPID, Res.ALIPAY_PRIVATEKEY, "json", "utf-8", Res.ALIPAY_PUBLICKEY, "RSA2");
        youChengAlipayClient = new DefaultAlipayClient(url, Res.YOUCHENG_ALIPAY_APPID, Res.YOUCHENG_ALIPAY_PRIVATEKEY, "json", "utf-8", Res.YOUCHENG_ALIPAY_PUBLICKEY, "RSA2");
    }

    @Override
    public MemberAlipayModel login(MemberAlipayLoginRequest request) {
        MemberAlipayModel memberAlipayModel = null;
        String authCode = request.getAuthCode();
        String appId = request.getAppId();
        String app_auth_token = request.getApp_auth_token();
        String scopes = request.getScopes();
        if (Res.YOUCHENG_ALIPAY_APPID.equals(appId)) {
            memberAlipayModel = loginWithOutThreePart(authCode, scopes);
        } else {
            if (app_auth_token == null) {
                throw new BaseException("6004003", "支付宝小程序授权code不能为空！");
            }
            memberAlipayModel = loginWithThreePart(authCode, app_auth_token, scopes);
        }
        return memberAlipayModel;
    }

    /**
     * 登录模式  非第三方代理
     *
     * @param authCode
     * @return
     */
    private MemberAlipayModel loginWithOutThreePart(String authCode, String scopes) {
        MemberAlipay memberAlipay = new MemberAlipay();
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCode);
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = youChengAlipayClient.execute(request);
        } catch (AlipayApiException e) {
            log.error("阿里云连接失败 " + e.getMessage());
            throw new BaseException("6004006", "系统异常 " + e.getMessage());
        }
        if (response.isSuccess()) {   //登录成功
            memberAlipay.setUserId(response.getUserId());
            if ("auth_user".equals(scopes)) {     //主动授权 获取用户信息
                AlipayUserInfoShareRequest shareRequest = new AlipayUserInfoShareRequest();
                AlipayUserInfoShareResponse shareResponse = null;
                try {
                    shareResponse = youChengAlipayClient.execute(shareRequest, response.getAccessToken());
                } catch (AlipayApiException e) {
                    log.error("阿里云 获取用户信息失败!  " + e.getMessage());
                }
                if (shareResponse != null && shareResponse.isSuccess()) {
                    memberAlipay = transform(shareResponse);        //转换用户信息
                } else {
                    log.error("阿里云 获取用户信息失败! ");
                }
            }
            return registerOrLogin(memberAlipay);
        } else {
            throw new BaseException("6004006", "支付宝授权登录失败");
        }
    }

    /**
     * 登录模式   为第三方代理模式
     *
     * @param authCode
     * @param app_auth_token
     * @return
     */
    private MemberAlipayModel loginWithThreePart(String authCode, String app_auth_token, String scopes) {
        MemberAlipayModel memberAlipayModel = null;
        MemberAlipay memberAlipay = new MemberAlipay();
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCode);
        request.putOtherTextParam("app_auth_token", app_auth_token);
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            log.error("阿里云连接失败 " + e.getMessage());
            throw new BaseException("6004006", "系统异常 " + e.getMessage());
        }
        if (response.isSuccess()) {   //登录成功
            memberAlipay.setUserId(response.getUserId());
            if ("auth_user".equals(scopes)) {    //主动授权 获取用户信息
                AlipayUserInfoShareRequest shareRequest = new AlipayUserInfoShareRequest();
                shareRequest.putOtherTextParam("app_auth_token", app_auth_token);         //授权token
                AlipayUserInfoShareResponse shareResponse = null;
                try {
                    shareResponse = alipayClient.execute(shareRequest, response.getAccessToken());
                } catch (AlipayApiException e) {
                    log.error("阿里云连接失败 " + e.getMessage());
                }
                if (shareResponse != null && shareResponse.isSuccess()) {
                    memberAlipay = transform(shareResponse);        //转换用户信息
                } else {
                    log.error("阿里云 获取用户信息失败! ");
                }
            }
            memberAlipayModel = registerOrLogin(memberAlipay);
        } else {
            throw new BaseException("6004006", "支付宝授权登录失败");
        }
        return memberAlipayModel;
    }

    @Override
    public MemberAlipayModel getMemberAlipayByMemberId(Long memberId) {
        MemberAlipayModel memberAlipayModel = new MemberAlipayModel();
        MemberAlipayExample example = new MemberAlipayExample();
        MemberAlipayExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberId);
        List<MemberAlipay> list = memberAlipayMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            MemberAlipay memberAlipay = list.get(0);
            BeanUtils.copyProperties(memberAlipay, memberAlipayModel);
        }else{
            throw  new BaseException("6004011","未查询到该用户记录！");
        }
        return memberAlipayModel;
    }

    /**
     * 支付宝响应数据转换用户信息
     *
     * @param response
     * @return
     */
    private MemberAlipay transform(AlipayUserInfoShareResponse response) {
        MemberAlipay memberAlipay = new MemberAlipay();
        if (response != null && response.isSuccess()) {
            memberAlipay.setUserId(response.getUserId());
            memberAlipay.setNickname(response.getNickName());
            memberAlipay.setSex(response.getGender());
            memberAlipay.setProvince(response.getProvince());
            memberAlipay.setCity(response.getCity());
            memberAlipay.setHeadimgurl(response.getAvatar());
            memberAlipay.setUserType(response.getUserType());
            memberAlipay.setUserStudentFlag(response.getIsStudentCertified());
            memberAlipay.setUserRealFlag(response.getIsCertified());
            memberAlipay.setCreateTime(new Date());
            memberAlipay.setLastTime(new Date());
            memberAlipay.setTotalCount(1);
        }
        return memberAlipay;
    }

    /**
     * 注册或登录用户
     *
     * @param memberAlipay
     * @return
     */
    private MemberAlipayModel registerOrLogin(MemberAlipay memberAlipay) {
        MemberAlipayModel memberAlipayModel = new MemberAlipayModel();
        String userId = memberAlipay.getUserId();
        synchronized (userId){
            MemberAlipayExample example = new MemberAlipayExample();
            MemberAlipayExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(memberAlipay.getUserId());
            List<MemberAlipay> list = memberAlipayMapper.selectByExample(example);
            if (list != null && list.size() > 0) {     //会员登录
                MemberAlipay member = list.get(0);
                member.setNickname(memberAlipay.getNickname());
                member.setSex(memberAlipay.getSex());
                member.setProvince(memberAlipay.getProvince());
                member.setCity(memberAlipay.getCity());
                member.setHeadimgurl(memberAlipay.getHeadimgurl());
                member.setUserType(memberAlipay.getUserType());
                member.setUserRealFlag(memberAlipay.getUserRealFlag());
                member.setUserStudentFlag(memberAlipay.getUserStudentFlag());
                member.setTotalCount(member.getTotalCount() + 1);
                member.setUpdateTime(new Date());
                memberAlipayMapper.updateByPrimaryKeySelective(member);
                BeanUtils.copyProperties(member, memberAlipayModel);
            } else {              //会员注册
                Member member = new Member();
                member.setSource(MemberSourceEnum.ALIPAY.getCode());
                member.setDeleted(MemberDeleteEnum.ACTIIVE.getCode());
                memberMapper.insert(member);
                memberAlipay.setMemberId(member.getId());
                memberAlipay.setCreateTime(new Date());
                memberAlipay.setTotalCount(1);
                memberAlipay.setUpdateTime(new Date());
                memberAlipayMapper.insertSelective(memberAlipay);
                BeanUtils.copyProperties(memberAlipay, memberAlipayModel);
            }
        }
        return memberAlipayModel;
    }

}
