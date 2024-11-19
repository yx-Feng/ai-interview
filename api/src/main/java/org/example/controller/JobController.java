package org.example.controller;

import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.bo.JobBO;
import org.example.result.GraceJSONResult;
import org.example.service.IJobService;
import org.example.utils.PagedGridResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("list")
    public GraceJSONResult list(@RequestParam(defaultValue = "1", name = "page") Integer page,
                                @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        PagedGridResult gridResult = jobService.queryList(page, pageSize);
        return GraceJSONResult.ok(gridResult);
    }

    /**
     * 获得职位详情
     * @return
     */
    @GetMapping("detail")
    public GraceJSONResult list(@RequestParam String jobId) {
        return GraceJSONResult.ok(jobService.getDetail(jobId));
    }

    @PostMapping ("delete")
    public GraceJSONResult delete(@RequestParam String jobId) {
        jobService.delete(jobId);
        return GraceJSONResult.ok();
    }
}
