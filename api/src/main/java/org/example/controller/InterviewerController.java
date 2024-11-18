package org.example.controller;

import jakarta.annotation.Resource;
import org.example.pojo.bo.InterviewBO;
import org.example.result.GraceJSONResult;
import org.example.service.IInterviewerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数字人面试官表 前端控制器
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@RestController
@RequestMapping("/interviewer")
public class InterviewerController {

    @Resource
    private IInterviewerService interviewerService;

    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody InterviewBO interviewBO) {
        interviewerService.createOrUpdate(interviewBO);
        return  GraceJSONResult.ok();
    }
}
