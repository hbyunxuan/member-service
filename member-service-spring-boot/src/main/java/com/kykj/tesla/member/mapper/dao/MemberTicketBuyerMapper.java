package com.kykj.tesla.member.mapper.dao;

import com.kykj.tesla.member.entity.MemberTicketBuyer;
import com.kykj.tesla.member.entity.MemberTicketBuyerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberTicketBuyerMapper {
    long countByExample(MemberTicketBuyerExample example);

    int deleteByExample(MemberTicketBuyerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberTicketBuyer record);

    int insertSelective(MemberTicketBuyer record);

    List<MemberTicketBuyer> selectByExample(MemberTicketBuyerExample example);

    MemberTicketBuyer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberTicketBuyer record, @Param("example") MemberTicketBuyerExample example);

    int updateByExample(@Param("record") MemberTicketBuyer record, @Param("example") MemberTicketBuyerExample example);

    int updateByPrimaryKeySelective(MemberTicketBuyer record);

    int updateByPrimaryKey(MemberTicketBuyer record);
}