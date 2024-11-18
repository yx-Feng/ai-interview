package org.example.service.impl;

import org.example.pojo.InterviewRecord;
import org.example.mapper.InterviewRecordMapper;
import org.example.service.IInterviewRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 面试记录表 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class InterviewRecordServiceImpl extends ServiceImpl<InterviewRecordMapper, InterviewRecord> implements IInterviewRecordService {

}
