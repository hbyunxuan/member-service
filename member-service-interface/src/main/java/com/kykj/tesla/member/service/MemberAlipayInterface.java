package com.kykj.tesla.member.service;

import com.kykj.tesla.member.service.request.MemberAlipayLoginRequest;
import com.kykj.tesla.member.service.response.MemberAlipayGetResponse;
import com.kykj.tesla.member.service.response.MemberAlipayLoginResponse;
import com.kykj.tesla.platform.service.response.ResultResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * alipay 支付宝小程序 会员接口
 */
public interface MemberAlipayInterface {

    /*
     * 支付宝会员登录
     * */
    @ApiOperation("支付宝会员登录")
    @RequestMapping(value = "/api/member/alipay/login", method = RequestMethod.POST)
    MemberAlipayLoginResponse login(@RequestBody MemberAlipayLoginRequest memberAlipayLoginRequest);

    /*
     * 会员退出
     * */
    @ApiOperation("会员退出")
    @RequestMapping(value = "/api/member/alipay/logout", method = RequestMethod.POST)
    ResultResponse logout(@RequestParam("memberId") Long memberId);

    /*
     * 会员信息查询
     * */
    @ApiOperation("会员信息查询")
    @RequestMapping(value = "/api/member/alipay/get", method = RequestMethod.POST)
    MemberAlipayGetResponse get(@RequestParam("memberId") Long memberId);

}
