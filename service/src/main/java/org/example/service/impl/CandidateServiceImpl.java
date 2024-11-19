package org.example.service.impl;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.pojo.Candidate;
import org.example.mapper.CandidateMapper;
import org.example.pojo.bo.CandidateBO;
import org.example.service.ICandidateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 应聘者表 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class CandidateServiceImpl extends ServiceImpl<CandidateMapper, Candidate> implements ICandidateService {

    @Resource
    private CandidateMapper candidateMapper;

    @Override
    public void createOrUpdate(CandidateBO candidateBO) {
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(candidateBO, candidate);
        candidate.setUpdatedTime(LocalDateTime.now());
        if(StringUtils.isBlank(candidate.getId())) {
            candidate.setCreatedTime(LocalDateTime.now());
            candidateMapper.insert(candidate);
        } else {
            candidateMapper.updateById(candidate);
        }
    }
}
