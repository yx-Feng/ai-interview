package org.example.service;

import org.example.pojo.Interviewer;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.bo.InterviewBO;

import java.util.List;

/**
 * <p>
 * 数字人面试官表 服务类
 * </p>
 */
public interface IInterviewerService extends IService<Interviewer> {
    public void createOrUpdate(InterviewBO interviewBO);

    public List<Interviewer> queryAll();

    public void delete(String interviewerId);
}
