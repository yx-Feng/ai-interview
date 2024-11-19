package org.example.service;

import org.example.pojo.Job;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.bo.JobBO;
import org.example.utils.PagedGridResult;

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
}
