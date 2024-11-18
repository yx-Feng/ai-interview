package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.example.pojo.bo.InterviewBO;
import org.example.result.GraceJSONResult;
import org.example.service.IInterviewerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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

    /**
     * 新增或修改面试官
     * @param interviewBO
     * @return
     */
    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@Valid  @RequestBody InterviewBO interviewBO) {
        interviewerService.createOrUpdate(interviewBO);
        return  GraceJSONResult.ok();
    }

    /**
     * 查询面试官列表
     * @return
     */
    @GetMapping("list")
    public GraceJSONResult list() {
        return GraceJSONResult.ok(interviewerService.queryAll());
    }

    @PostMapping("delete")
    public GraceJSONResult delete(@RequestParam String interviewerId) {
        interviewerService.delete(interviewerId);
        return GraceJSONResult.ok();
    }
}
