package org.example.service.impl;

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

}
