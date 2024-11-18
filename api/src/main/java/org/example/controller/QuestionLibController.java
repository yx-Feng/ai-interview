package org.example.controller;

import jakarta.annotation.Resource;
import org.example.pojo.QuestionLib;
import org.example.pojo.bo.QuestionLibBO;
import org.example.result.GraceJSONResult;
import org.example.service.IQuestionLibService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题） 前端控制器
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Controller
@RequestMapping("/questionLib")
public class QuestionLibController {

    @Resource
    private IQuestionLibService questionLibService;

    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody QuestionLibBO questionLibBO) {
        questionLibService.createOrUpdate(questionLibBO);
        return GraceJSONResult.ok();
    }
}
