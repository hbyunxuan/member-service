package com.kykj.tesla.member.mapper.dao;

import com.kykj.tesla.member.entity.MemberAlipay;
import com.kykj.tesla.member.entity.MemberAlipayExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberAlipayMapper {
    long countByExample(MemberAlipayExample example);

    int deleteByExample(MemberAlipayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberAlipay record);

    int insertSelective(MemberAlipay record);

    List<MemberAlipay> selectByExample(MemberAlipayExample example);

    MemberAlipay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberAlipay record, @Param("example") MemberAlipayExample example);

    int updateByExample(@Param("record") MemberAlipay record, @Param("example") MemberAlipayExample example);

    int updateByPrimaryKeySelective(MemberAlipay record);

    int updateByPrimaryKey(MemberAlipay record);

}