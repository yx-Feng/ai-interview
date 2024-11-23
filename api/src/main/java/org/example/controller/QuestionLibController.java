package org.example.controller;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.enums.YesOrNo;
import org.example.pojo.QuestionLib;
import org.example.pojo.bo.QuestionLibBO;
import org.example.pojo.vo.InitQuestionsVO;
import org.example.result.GraceJSONResult;
import org.example.result.ResponseStatusEnum;
import org.example.service.IQuestionLibService;
import org.example.utils.PagedGridResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题） 前端控制器
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@RestController
@RequestMapping("/questionLib")
public class QuestionLibController extends BaseInfoProperties {

    @Resource
    private IQuestionLibService questionLibService;

    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody QuestionLibBO questionLibBO) {
        questionLibService.createOrUpdate(questionLibBO);
        return GraceJSONResult.ok();
    }

    @GetMapping("list")
    public GraceJSONResult list(@RequestParam String aiName, @RequestParam String question,
                                @RequestParam(defaultValue = "1", name = "page") Integer page,
                                @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        PagedGridResult gridResult = questionLibService.queryList(aiName, question, page, pageSize);
        return GraceJSONResult.ok(gridResult);
    }

    // 设置显示
    @PostMapping("show")
    public GraceJSONResult show(@RequestParam String questionLibId) {
        if (StringUtils.isBlank(questionLibId)) return GraceJSONResult.error();
        questionLibService.setDisplayOrNot(questionLibId, YesOrNo.YES.type);
        return GraceJSONResult.ok();
    }

    // 设置隐藏
    @PostMapping("hide")
    public GraceJSONResult hide(@RequestParam String questionLibId) {
        if (StringUtils.isBlank(questionLibId)) return GraceJSONResult.error();
        questionLibService.setDisplayOrNot(questionLibId, YesOrNo.NO.type);
        return GraceJSONResult.ok();
    }

    @PostMapping("delete")
    public GraceJSONResult delete(@RequestParam String questionLibId) {
        questionLibService.delete(questionLibId);
        return GraceJSONResult.ok();
    }

    // 准备面试题，随机获得一定数量的面试题，返回给前端
    @GetMapping("prepareQuestion")
    public GraceJSONResult prepareQuestion(@RequestParam String candidateId) {
        // 判断应聘者候选人是否在会话中，限制接口被恶意调用
        String candidateInfo = redis.get(REDIS_USER_INFO+":"+candidateId);
        String userToken = redis.get(REDIS_USER_TOKEN+":"+candidateId);
        if(StringUtils.isBlank(candidateInfo) || StringUtils.isBlank(userToken)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.USER_INFO_NOT_EXIST_ERROR);
        }
        List<InitQuestionsVO> result = questionLibService.getRandomQuestions(candidateId, 10);

        return GraceJSONResult.ok(result);
    }
}
