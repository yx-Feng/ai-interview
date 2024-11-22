package org.example.controller;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.result.GraceJSONResult;
import org.example.utils.SMSUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("welcome")
public class WelcomeController {

    @Resource
    private SMSUtils smsUtils;

    @PostMapping("getSMSCode")
    public GraceJSONResult getSMSCode(String mobile) throws Exception {
        if(StringUtils.isBlank(mobile)) {
            return GraceJSONResult.error();
        }
        String code = (int)((Math.random() * 9 + 1) * 100000) + ""; // 随机生成6位验证码
        smsUtils.sendSMS(mobile, code);
        //System.out.println(code);
        return  GraceJSONResult.ok();
    }

    @PostMapping("verify")
    public GraceJSONResult verify(String mobile) {
        // 1. 从redis中获得验证码进行校验判断是否匹配
        // 2. 根据mobile查询数据库，判断用户是否存在，是否是候选人
            // 2.1 如果查询的用户为空，则表示该用户不是候选人，无法进入面试流程
            // 2.2 如果不为空，则需要判断用户是否已经面试过，如果面试过，则无法再面试

        // 3. 保存用户token信息，保存分布式会话到Redis中

        // 4. 用户进入面试流程后（登录之后），删除Redis中的验证码

        // 5. 返回用户信息给前端

        // 6. (可选) 用户信息保存到服务器Redis中，保存8小时或4小时

        return GraceJSONResult.ok();
    }
}
