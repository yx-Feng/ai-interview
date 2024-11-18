package org.example.service.impl;

import org.example.pojo.Interviewer;
import org.example.mapper.InterviewerMapper;
import org.example.service.IInterviewerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数字人面试官表 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class InterviewerServiceImpl extends ServiceImpl<InterviewerMapper, Interviewer> implements IInterviewerService {

}
