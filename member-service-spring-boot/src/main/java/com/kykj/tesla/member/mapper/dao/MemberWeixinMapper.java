package com.kykj.tesla.member.mapper.dao;

import com.kykj.tesla.member.entity.MemberWeixin;
import com.kykj.tesla.member.entity.MemberWeixinExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberWeixinMapper {
    long countByExample(MemberWeixinExample example);

    int deleteByExample(MemberWeixinExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberWeixin record);

    int insertSelective(MemberWeixin record);

    List<MemberWeixin> selectByExample(MemberWeixinExample example);

    MemberWeixin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberWeixin record, @Param("example") MemberWeixinExample example);

    int updateByExample(@Param("record") MemberWeixin record, @Param("example") MemberWeixinExample example);

    int updateByPrimaryKeySelective(MemberWeixin record);

    int updateByPrimaryKey(MemberWeixin record);
}