package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.mapper.JobMapperCustom;
import org.example.pojo.Job;
import org.example.mapper.JobMapper;
import org.example.pojo.bo.JobBO;
import org.example.pojo.vo.JobVO;
import org.example.service.IJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 职位信息表 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class JobServiceImpl extends BaseInfoProperties implements IJobService {

    @Resource
    private JobMapper jobMapper;

    @Resource
    private JobMapperCustom jobMapperCustom;

    @Override
    public void createOrUpdate(JobBO jobBO) {
        Job job = new Job();
        BeanUtils.copyProperties(jobBO, job);
        job.setUpdatedTime(LocalDateTime.now());
        if(StringUtils.isBlank(job.getId())) {
            job.setCreateTime(LocalDateTime.now());
            jobMapper.insert(job);
        } else {
            jobMapper.updateById(job);
        }
    }

    @Override
    public PagedGridResult queryList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<JobVO> list = jobMapperCustom.queryJobList(null);
        return setterPagedGrid(list, page);
    }

    @Override
    public Job getDetail(String jobId) {
        return jobMapper.selectById(jobId);
    }

    @Override
    public void delete(String jobId) {
        // 删除职位的时候，判断有没有未面试的候选人在使用
        jobMapper.deleteById(jobId);
    }

    @Override
    public boolean isJobContainInterviewer(String interviewerId) {
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interviewer_id", interviewerId);
        Long counts = jobMapper.selectCount(queryWrapper);
        return counts > 0 ? true : false;
    }
}
