package com.kykj.tesla.member.service;


import com.kykj.tesla.member.service.request.MemberWeixinGetUnionIdRequest;
import com.kykj.tesla.member.service.request.MemberWeixinLoginRequest;
import com.kykj.tesla.member.service.request.MemberWeixinSaveRequest;
import com.kykj.tesla.member.service.response.MemberWeixinGetResponse;
import com.kykj.tesla.member.service.response.MemberWeixinLoginResponse;
import com.kykj.tesla.platform.service.response.ResultResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * weixin  微信小程序 会员接口
 */
public interface MemberWeixinInterface {

    @ApiOperation("微信会员登录")
    @RequestMapping(value = "/api/member/weixin/login", method = RequestMethod.POST)
    MemberWeixinLoginResponse login(@RequestBody MemberWeixinLoginRequest request);

    @ApiOperation("微信会员退出")
    @RequestMapping(value = "/api/member/weixin/logout", method = RequestMethod.POST)
    ResultResponse logout(@RequestParam("memberId") Long memberId);

    @ApiOperation("微信会员信息保存")
    @RequestMapping(value = "/api/member/weixin/save", method = RequestMethod.POST)
    ResultResponse save(@RequestBody MemberWeixinSaveRequest request);

    @ApiOperation("会员信息查询")
    @RequestMapping(value = "/api/member/weixin/get", method = RequestMethod.POST)
    MemberWeixinGetResponse get(@RequestParam("memberId") Long memberId);

    @ApiOperation("会员unionId解密")
    @RequestMapping(value = "/api/member/weixin/get_union_id", method = RequestMethod.POST)
    MemberWeixinGetResponse getUnionId(@RequestBody MemberWeixinGetUnionIdRequest request );

}
