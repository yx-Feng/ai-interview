package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.example.pojo.InterviewRecord;
import org.example.mapper.InterviewRecordMapper;
import org.example.service.IInterviewRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 面试记录表 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class InterviewRecordServiceImpl implements IInterviewRecordService {

    @Resource
    private InterviewRecordMapper interviewRecordMapper;

    @Override
    public void save(InterviewRecord interviewRecord) {
        interviewRecordMapper.insert(interviewRecord);
    }

    @Override
    public boolean isCandidateRecordExist(String candidateId) {
        List<InterviewRecord> list = interviewRecordMapper.selectList(new QueryWrapper<InterviewRecord>().eq("candidate_id", candidateId));
        if(list.isEmpty() || list.size() == 0) return false;
        return true;
    }
}
