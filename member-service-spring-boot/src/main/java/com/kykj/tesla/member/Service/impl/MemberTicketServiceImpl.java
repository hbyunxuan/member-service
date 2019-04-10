package com.kykj.tesla.member.Service.impl;

import com.kykj.tesla.member.Service.MemberTicketService;
import com.kykj.tesla.member.entity.MemberTicketBuyer;
import com.kykj.tesla.member.entity.MemberTicketBuyerExample;
import com.kykj.tesla.member.mapper.dao.MemberTicketBuyerMapper;
import com.kykj.tesla.member.service.model.MemberTicketBuyerModel;
import com.kykj.tesla.member.service.request.MemberTicketBuyerSaveRequest;
import com.kykj.tesla.platform.service.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MemberTicketServiceImpl implements MemberTicketService {

    @Autowired
    @SuppressWarnings("all")
    private MemberTicketBuyerMapper memberTicketBuyerMapper;

    @Override
    public boolean save(MemberTicketBuyerSaveRequest request) {
        MemberTicketBuyerModel memberTicketBuyerModel = request.getMemberTicket();
        MemberTicketBuyer memberTicketBuyer = new MemberTicketBuyer() ;
        BeanUtils.copyProperties(memberTicketBuyerModel,memberTicketBuyer);
        memberTicketBuyer.setMemberId(request.getMemberId());
        MemberTicketBuyerExample example = new MemberTicketBuyerExample();
        MemberTicketBuyerExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(request.getMemberId());
        criteria.andIdcardEqualTo(request.getMemberTicket().getIdcard());
        List<MemberTicketBuyer> list = memberTicketBuyerMapper.selectByExample(example);
        if(list != null && list.size() >0 ){
            memberTicketBuyer.setId(list.get(0).getId());
            memberTicketBuyerMapper.updateByPrimaryKeySelective(memberTicketBuyer);
        }else{
            memberTicketBuyerMapper.insertSelective(memberTicketBuyer);
        }
        return true;
    }

    @Override
    public List<MemberTicketBuyerModel> getMemberTicketBuyerListByMemberId(Long memberId) {
        List<MemberTicketBuyerModel> modelList =  new ArrayList<MemberTicketBuyerModel>();
        MemberTicketBuyerExample example = new MemberTicketBuyerExample();
        MemberTicketBuyerExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberId);
        List<MemberTicketBuyer> list = memberTicketBuyerMapper.selectByExample(example);
        for(MemberTicketBuyer memberTicketBuyer : list){
            MemberTicketBuyerModel model = new MemberTicketBuyerModel();
            BeanUtils.copyProperties(memberTicketBuyer,model);
            modelList.add(model);
        }
        return modelList;
    }
}
