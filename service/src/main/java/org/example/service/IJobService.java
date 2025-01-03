package org.example.service;

import org.example.pojo.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.bo.JobBO;
import org.example.utils.PagedGridResult;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 职位信息表 服务类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public interface IJobService {
    public void createOrUpdate(JobBO jobBO);

    public PagedGridResult queryList(Integer page, Integer pageSize);

    public Job getDetail(String jobId);

    public void delete(String jobId);

    // 判断所有职位是否包含某个面试官
    public boolean isJobContainInterviewer(String interviewerId);

    public List<HashMap<String, String>> nameList();
}
