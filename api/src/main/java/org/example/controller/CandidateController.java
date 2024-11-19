package org.example.controller;

import jakarta.annotation.Resource;
import org.example.pojo.bo.CandidateBO;
import org.example.result.GraceJSONResult;
import org.example.service.ICandidateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
