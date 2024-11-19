package org.example.service;

import org.example.pojo.Candidate;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.bo.CandidateBO;
import org.example.utils.PagedGridResult;

/**
 * <p>
 * 应聘者表 服务类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public interface ICandidateService {
    public void createOrUpdate(CandidateBO candidateBO);

    public PagedGridResult queryList(String realName, String mobile, Integer page, Integer pageSize);
}
