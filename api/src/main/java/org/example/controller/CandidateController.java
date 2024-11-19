package org.example.controller;

import jakarta.annotation.Resource;
import org.example.pojo.bo.CandidateBO;
import org.example.result.GraceJSONResult;
import org.example.service.ICandidateService;
import org.example.utils.PagedGridResult;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 应聘者表 前端控制器
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Resource
    private ICandidateService candidateService;

    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody CandidateBO candidateBO) {
        candidateService.createOrUpdate(candidateBO);
        return GraceJSONResult.ok();
    }

    @GetMapping("list")
    public GraceJSONResult list(@RequestParam String realName, @RequestParam String mobile,
                                @RequestParam(defaultValue = "1", name = "page") Integer page,
                                @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        PagedGridResult gridResult = candidateService.queryList(realName, mobile, page, pageSize);
        return GraceJSONResult.ok(gridResult);
    }
}
