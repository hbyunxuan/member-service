package com.kykj.tesla.member.Service;


import com.kykj.tesla.member.service.model.MemberAlipayModel;
import com.kykj.tesla.member.service.request.MemberAlipayLoginRequest;

public interface MemberAlipayService {
    MemberAlipayModel login(MemberAlipayLoginRequest request);

    MemberAlipayModel getMemberAlipayByMemberId(Long memberId);

}
