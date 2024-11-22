package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.mapper.CandidateMapperCustom;
import org.example.pojo.Candidate;
import org.example.mapper.CandidateMapper;
import org.example.pojo.bo.CandidateBO;
import org.example.pojo.vo.CandidateVO;
import org.example.service.ICandidateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 应聘者表 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class CandidateServiceImpl extends BaseInfoProperties implements ICandidateService {

    @Resource
    private CandidateMapper candidateMapper;

    @Resource
    private CandidateMapperCustom candidateMapperCustom;

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

    @Override
    public PagedGridResult queryList(String realName, String mobile, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(realName)) {
            map.put("realName", realName);
        }
        if(StringUtils.isNotBlank(mobile)) {
            map.put("mobile", mobile);
        }
        List<CandidateVO> list = candidateMapperCustom.queryCandidateLibList(map);
        return setterPagedGrid(list, page);
    }

    @Override
    public Candidate getDetail(String candidateId) {
        return candidateMapper.selectById(candidateId);
    }

    @Override
    public void delete(String candidateId) {
        candidateMapper.deleteById(candidateId);
    }

    @Override
    public Candidate queryByMobileIsExist(String mobile) {
        Candidate candidate = candidateMapper.selectOne(new QueryWrapper<Candidate>().eq("mobile", mobile));
        return candidate;
    }

}
