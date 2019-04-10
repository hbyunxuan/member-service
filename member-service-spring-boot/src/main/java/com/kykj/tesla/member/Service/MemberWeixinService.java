package com.kykj.tesla.member.Service;

import com.kykj.tesla.member.service.model.MemberWeixinModel;
import com.kykj.tesla.member.service.request.MemberWeixinGetUnionIdRequest;
import com.kykj.tesla.member.service.request.MemberWeixinLoginRequest;

public interface MemberWeixinService {

    MemberWeixinModel login(MemberWeixinLoginRequest request);

    boolean save(MemberWeixinModel memberWeixinModel);

    MemberWeixinModel get(Long memberId);

    MemberWeixinModel getUnionId(MemberWeixinGetUnionIdRequest request);

}
