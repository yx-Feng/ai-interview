package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.example.exception.GraceException;
import org.example.pojo.Interviewer;
import org.example.mapper.InterviewerMapper;
import org.example.pojo.bo.InterviewBO;
import org.example.result.GraceJSONResult;
import org.example.result.ResponseStatusEnum;
import org.example.service.IInterviewerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.service.IJobService;
import org.example.service.IQuestionLibService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Resource
    private InterviewerMapper interviewerMapper;

    @Resource
    private IJobService jobService;

    @Resource
    private IQuestionLibService questionLibService;

    @Override
    public void createOrUpdate(InterviewBO interviewBO) {
        Interviewer interviewer = new Interviewer();
        BeanUtils.copyProperties(interviewBO, interviewer);
        interviewer.setUpdatedTime(LocalDateTime.now());
        if(StringUtils.isBlank(interviewer.getId())) {
            interviewer.setCreateTime(LocalDateTime.now());
            interviewerMapper.insert(interviewer);
        } else {
            interviewerMapper.updateById(interviewer);
        }
    }

    @Override
    public List<Interviewer> queryAll() {
        return interviewerMapper.selectList(new QueryWrapper<Interviewer>().orderByDesc("updated_time"));
    }

    @Override
    public void delete(String interviewerId) {
        // 删除面试官需要判断有没有职位和题库正在使用
        boolean jobFlag = jobService.isJobContainInterviewer(interviewerId);
        boolean questionFlag = questionLibService.isQuestionLibContainInterviewer(interviewerId);
        if(jobFlag || questionFlag) {
            GraceException.display(ResponseStatusEnum.CAN_NOT_DELETE_INTERVIEWER);
        }
        interviewerMapper.deleteById(interviewerId);
    }
}
