package org.example.controller;

import jakarta.annotation.Resource;
import org.example.pojo.bo.JobBO;
import org.example.result.GraceJSONResult;
import org.example.service.IJobService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 职位信息表 前端控制器
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private IJobService jobService;

    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody JobBO jobBO) {
        jobService.createOrUpdate(jobBO);
        return GraceJSONResult.ok();
    }
}
