package org.example.service;

import org.example.pojo.InterviewRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.utils.PagedGridResult;

/**
 * <p>
 * 面试记录表 服务类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public interface IInterviewRecordService {

    public void save(InterviewRecord interviewRecord);

    public boolean isCandidateRecordExist(String candidateId);

    public PagedGridResult queryList(String realName, String mobile, Integer page, Integer pageSize);
}
