package com.kykj.tesla.member.Service.impl;

import com.kykj.tesla.application.service.response.ApplicationAppletWeixinComponentAccessTokenGetResponse;
import com.kykj.tesla.member.Service.ApplicationAppletWeixinService;
import com.kykj.tesla.member.Service.MemberWeixinService;
import com.kykj.tesla.member.common.HttpUtil;
import com.kykj.tesla.member.common.Res;
import com.kykj.tesla.member.entity.Member;
import com.kykj.tesla.member.entity.MemberWeixin;
import com.kykj.tesla.member.entity.MemberWeixinExample;
import com.kykj.tesla.member.enums.MemberDeleteEnum;
import com.kykj.tesla.member.enums.MemberSourceEnum;
import com.kykj.tesla.member.mapper.dao.MemberMapper;
import com.kykj.tesla.member.mapper.dao.MemberWeixinMapper;
import com.kykj.tesla.member.service.model.MemberWeixinModel;
import com.kykj.tesla.member.service.request.MemberWeixinGetUnionIdRequest;
import com.kykj.tesla.member.service.request.MemberWeixinLoginRequest;
import com.kykj.tesla.member.util.AES;
import com.kykj.tesla.platform.service.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MemberWeixinServiceImpl implements MemberWeixinService {

    @Autowired
    @SuppressWarnings("all")
    private MemberMapper memberMapper;

    @Autowired
    @SuppressWarnings("all")
    private MemberWeixinMapper memberWeixinMapper;

    @Autowired
    @SuppressWarnings("all")
    private ApplicationAppletWeixinService applicationAppletWeixinService;

    @Override
    @Transactional
    public MemberWeixinModel login(MemberWeixinLoginRequest request) {
        MemberWeixinModel memberWeixinModel = new MemberWeixinModel();
        String url = null;
        if (Res.YOUCHENG_WEIXIN_APPID.equalsIgnoreCase(request.getAppId())) {    //游橙小程序会员登录
            String data = "appid=" + Res.YOUCHENG_WEIXIN_APPID + "&secret=" + Res.YOUCHENG_WEIXIN_APPSECRET +
                    "&js_code=" + request.getCode() + "&grant_type=authorization_code";
            url = "https://api.weixin.qq.com/sns/jscode2session?" + data;
        } else {
            ApplicationAppletWeixinComponentAccessTokenGetResponse response = applicationAppletWeixinService.getComponentAccessToken();
            String component_access_token = response.getComponentAccessToken();
            String data = "appid=" + request.getAppId() + "&js_code=" + request.getCode() + "&" +
                    "grant_type=authorization_code&component_appid=" + Res.WEIXIN_APPID + "&component_access_token="
                    + component_access_token;
            url = "https://api.weixin.qq.com/sns/component/jscode2session?" + data;
        }
        log.info("访问微信服务器请求路径 " + url);
        String responseText = HttpUtil.httpGET(url);
        JSONObject response = new JSONObject(responseText);
        String openId = "";
        String sessionKey = "";
        try {
            openId = response.getString("openid");
            sessionKey = response.getString("session_key");
        } catch (Exception e) {
            throw new BaseException("6004008", "微信授权登录失败！");
        }
        MemberWeixin memberWeixin = loginOrRegister(openId, sessionKey);
        BeanUtils.copyProperties(memberWeixin, memberWeixinModel);
        return memberWeixinModel;
    }

    /**
     * 登录或注册微信用户
     *
     * @param openId
     * @param sessionKey
     * @return
     */
    public MemberWeixin loginOrRegister(String openId, String sessionKey) {
        MemberWeixin memberWeixin = null;
        synchronized (openId){
            MemberWeixinExample example = new MemberWeixinExample();
            MemberWeixinExample.Criteria criteria = example.createCriteria();
            criteria.andOpenIdEqualTo(openId);
            List<MemberWeixin> list = memberWeixinMapper.selectByExample(example);
            if (list != null && list.size() > 0) {     //登录
                memberWeixin = list.get(0);
                memberWeixin.setLastTime(new Date());
                memberWeixin.setTotalCount(memberWeixin.getTotalCount() + 1);
                memberWeixin.setSessionKey(sessionKey);
                memberWeixinMapper.updateByPrimaryKeySelective(memberWeixin);
            } else {      //注册
                Member member = new Member();
                member.setSource(MemberSourceEnum.WEIXIN.getCode());
                member.setDeleted(MemberDeleteEnum.ACTIIVE.getCode());
                memberMapper.insert(member);
                memberWeixin = new MemberWeixin();
                memberWeixin.setMemberId(member.getId());
                memberWeixin.setOpenId(openId);
                memberWeixin.setLastTime(new Date());
                memberWeixin.setTotalCount(1);
                memberWeixin.setSessionKey(sessionKey);
                memberWeixin.setCreateTime(new Date());
                memberWeixinMapper.insertSelective(memberWeixin);
            }
        }
        return memberWeixin;
    }

    @Override
    public boolean save(MemberWeixinModel memberWeixinModel) {
        MemberWeixinExample example = new MemberWeixinExample();
        MemberWeixinExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberWeixinModel.getMemberId());
        List<MemberWeixin> list = memberWeixinMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            MemberWeixin memberWeixin = list.get(0);
            BeanUtils.copyProperties(memberWeixinModel, memberWeixin);
            memberWeixinMapper.updateByPrimaryKeySelective(memberWeixin);
        }else{
            throw  new BaseException("6004011","未查询到该用户记录！");
        }
        return true;
    }

    @Override
    public MemberWeixinModel get(Long memberId) {
        MemberWeixinModel memberWeixinModel = new MemberWeixinModel();
        MemberWeixinExample example = new MemberWeixinExample();
        MemberWeixinExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberId);
        List<MemberWeixin> list = memberWeixinMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            MemberWeixin memberWeixin = list.get(0);
            BeanUtils.copyProperties(memberWeixin, memberWeixinModel);
        }else{
            throw  new BaseException("6004011","未查询到该用户记录！");
        }
        return memberWeixinModel;
    }

    @Override
    public MemberWeixinModel getUnionId(MemberWeixinGetUnionIdRequest request) {
        MemberWeixinModel model = new MemberWeixinModel();
        Long memberId = request.getMemberId();
        String encryptedData = request.getEncryptedData();
        String iv = request.getIv();
        MemberWeixinExample example = new MemberWeixinExample();
        MemberWeixinExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberId);
        List<MemberWeixin> list = memberWeixinMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            MemberWeixin memberWeixin = list.get(0);
            String sessionKey = memberWeixin.getSessionKey();
            Base64.Decoder decoder = Base64.getDecoder();
            try {
                byte[] result = AES.decrypt(decoder.decode(encryptedData), decoder.decode(sessionKey),AES.generateIV(decoder.decode(iv)));
                JSONObject response = new JSONObject(new String(result,"UTF-8"));
                memberWeixin.setNickName(response.getString("nickName"));
                memberWeixin.setGender((byte)response.getInt("gender"));
                memberWeixin.setCountry(response.getString("country"));
                memberWeixin.setUnionId(response.getString("unionId"));
                memberWeixin.setCity(response.getString("city"));
                memberWeixin.setAvatarUrl(response.getString("avatarUrl"));
                memberWeixinMapper.updateByPrimaryKeySelective(memberWeixin);
                BeanUtils.copyProperties(memberWeixin, model);
            } catch (Exception e) {
                throw  new BaseException("6004010","解密出错！");
            }
        }else{
            throw  new BaseException("6004011","未查询到该用户记录！");
        }
        return model;
    }
}
