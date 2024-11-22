package org.example.controller;

import com.google.common.base.Verify;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.pojo.Candidate;
import org.example.pojo.bo.CandidateBO;
import org.example.pojo.bo.VerifySMSBO;
import org.example.pojo.vo.CandidateVO;
import org.example.result.GraceJSONResult;
import org.example.result.ResponseStatusEnum;
import org.example.service.ICandidateService;
import org.example.utils.JsonUtils;
import org.example.utils.SMSUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("welcome")
public class WelcomeController extends BaseInfoProperties {

    @Resource
    private SMSUtils smsUtils;

    @Resource
    private ICandidateService candidateService;

    @PostMapping("getSMSCode")
    public GraceJSONResult getSMSCode(String mobile) throws Exception {
        if(StringUtils.isBlank(mobile)) {
            return GraceJSONResult.error();
        }
        String code = (int)((Math.random() * 9 + 1) * 100000) + ""; // 随机生成6位验证码
        // smsUtils.sendSMS(mobile, code);
        System.out.println(code);

        // 把验证码存入redis中，用于后续进入面试的校验
        redis.set(MOBILE_SMSCODE + ":" + mobile, code, 10*60);

        return  GraceJSONResult.ok();
    }

    // 校验用户能否进入面试流程
    @PostMapping("verify")
    public GraceJSONResult verify(@Validated @RequestBody VerifySMSBO verifySMSBO) {

        String mobile = verifySMSBO.getMobile();
        String code = verifySMSBO.getSmsCode();

        // 1. 从redis中获得验证码进行校验判断是否匹配
        String redisCode = redis.get(MOBILE_SMSCODE + ":" + mobile);
        if(StringUtils.isBlank(redisCode) || !redisCode.equalsIgnoreCase(code)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.SMS_CODE_ERROR);
        }

        // 2. 根据mobile查询数据库，判断用户是否存在，是否是候选人
        Candidate candidate = candidateService.queryByMobileIsExist(mobile);
        if(candidate == null) {
            // 2.1 如果查询的用户为空，则表示该用户不是候选人，无法进入面试流程
            return GraceJSONResult.errorCustom(ResponseStatusEnum.USER_INFO_NOT_EXIST_ERROR);
        } else {
            // 2.2 如果不为空，则需要判断用户是否已经面试过，如果面试过，则无法再面试
        }

        // 3. 保存用户token信息，保存分布式会话到Redis中
        String uToken = UUID.randomUUID().toString();
        redis.set(REDIS_USER_TOKEN + ":" + candidate.getId(), uToken, 3*60*60);

        CandidateVO candidateVO = new CandidateVO();
        BeanUtils.copyProperties(candidate, candidateVO);
        candidateVO.setUserToken(uToken);
        candidateVO.setCandidateId(candidate.getId());

        // 4. 用户进入面试流程后（登录之后），删除Redis中的验证码
        redis.del(MOBILE_SMSCODE+":"+mobile);

        // 5. 返回用户信息给前端

        // 6. (可选) 用户信息保存到服务器Redis中，保存8小时或4小时
        redis.set(REDIS_USER_INFO+":"+candidate.getId(), JsonUtils.objectToJson(candidateVO), 3*60*60);

        return GraceJSONResult.ok(candidateVO);
    }
}
