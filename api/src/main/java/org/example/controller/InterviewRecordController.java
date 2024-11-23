package org.example.controller;

import jakarta.annotation.Resource;
import org.example.ChatGLMTask;
import org.example.pojo.bo.SubmitAnswerBO;
import org.example.result.GraceJSONResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 面试记录表 前端控制器
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@RestController
@RequestMapping("/interviewRecord")
public class InterviewRecordController {

    @Resource
    private ChatGLMTask chatGLMTask;

    @PostMapping("collect")
    public GraceJSONResult collect(@RequestBody SubmitAnswerBO submitAnswerBO) throws Exception{
        chatGLMTask.display(submitAnswerBO);
        return GraceJSONResult.ok();
    }

}
