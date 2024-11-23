package org.example.controller;

import jakarta.annotation.Resource;
import org.example.ChatGLMTask;
import org.example.pojo.bo.SubmitAnswerBO;
import org.example.result.GraceJSONResult;
import org.example.service.IInterviewRecordService;
import org.example.utils.PagedGridResult;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private IInterviewRecordService interviewRecordService;

    @PostMapping("collect")
    public GraceJSONResult collect(@RequestBody SubmitAnswerBO submitAnswerBO) throws Exception{
        chatGLMTask.display(submitAnswerBO);
        return GraceJSONResult.ok();
    }

    @GetMapping("list")
    public GraceJSONResult list(@RequestParam String realName, @RequestParam String mobile, @RequestParam(defaultValue = "1", name = "page") Integer page, @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        PagedGridResult gridResult = interviewRecordService.queryList(realName, mobile, page, pageSize);
        return GraceJSONResult.ok(gridResult);
    }

}
