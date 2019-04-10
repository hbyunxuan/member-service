package com.kykj.tesla.member.Service;

import com.kykj.tesla.member.service.model.MemberTicketBuyerModel;
import com.kykj.tesla.member.service.request.MemberTicketBuyerSaveRequest;

import java.util.List;

public interface MemberTicketService {

    boolean save(MemberTicketBuyerSaveRequest request);

    List<MemberTicketBuyerModel> getMemberTicketBuyerListByMemberId(Long memberId);

}
