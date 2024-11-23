package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.enums.YesOrNo;
import org.example.mapper.QuestionLibMapperCustom;
import org.example.pojo.Job;
import org.example.pojo.QuestionLib;
import org.example.mapper.QuestionLibMapper;
import org.example.pojo.bo.QuestionLibBO;
import org.example.pojo.vo.InitQuestionsVO;
import org.example.pojo.vo.QuestionLibVO;
import org.example.service.ICandidateService;
import org.example.service.IJobService;
import org.example.service.IQuestionLibService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题） 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class QuestionLibServiceImpl extends BaseInfoProperties implements IQuestionLibService {

    @Resource
    private QuestionLibMapper questionLibMapper;

    @Resource
    private QuestionLibMapperCustom questionLibMapperCustom;

    @Resource
    private ICandidateService candidateService;

    @Resource
    private IJobService jobService;

    @Override
    public void createOrUpdate(QuestionLibBO questionLibBO) {
        QuestionLib questionLib = new QuestionLib();
        BeanUtils.copyProperties(questionLibBO, questionLib);
        questionLib.setUpdatedTime(LocalDateTime.now());

        if(StringUtils.isBlank(questionLib.getId())) {
            questionLib.setIsOn(YesOrNo.YES.type);
            questionLib.setCreateTime(LocalDateTime.now());
            questionLibMapper.insert(questionLib);
        } else {
            questionLibMapper.updateById(questionLib);
        }
    }

    @Override
    public PagedGridResult queryList(String aiName, String question, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize); // 原理：拦截sql，拼接分页参数
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNoneBlank(aiName)) {
            map.put("aiName", aiName);
        }
        if(StringUtils.isNoneBlank(question)) {
            map.put("question", question);
        }
        List<QuestionLibVO> list = questionLibMapperCustom.queryQuestionLibList(map);
        return setterPagedGrid(list, page);
    }

    @Override
    public void setDisplayOrNot(String questionLibId, Integer isOn) {
        QuestionLib questionLib = new QuestionLib();
        questionLib.setId(questionLibId);
        questionLib.setIsOn(isOn);
        questionLib.setUpdatedTime(LocalDateTime.now());
        questionLibMapper.updateById(questionLib);
    }

    @Override
    public void delete(String questionLibId) {
        questionLibMapper.deleteById(questionLibId);
    }

    @Override
    public boolean isQuestionLibContainInterviewer(String interviewerId) {
        QueryWrapper<QuestionLib> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interviewer_id", interviewerId);
        Long counts = questionLibMapper.selectCount(queryWrapper);
        return counts > 0 ? true : false;
    }

    @Override
    public List<InitQuestionsVO> getRandomQuestions(String candidateId, Integer questionNum) {
        // 1. 获得负责面试该应聘者的面试官
        String jobId = candidateService.getDetail(candidateId).getJobId();
        String interviewId = jobService.getDetail(jobId).getInterviewerId();

        // 2. 根据面试官获得其所有面试题总数
        Long questionCounts = questionLibMapper.selectCount(new QueryWrapper<QuestionLib>().eq("interviewer_id", interviewId));

        // 3. 根据题库总数获得指定数量的面试题
        List<Long> randomList = new ArrayList<>();
        for (int i = 0; i < questionNum; i++) {
            Random random = new Random();
            long randomNum = random.nextLong(questionCounts);
            if(randomList.contains(randomNum)) {
                // 如果包含则继续循环
                questionNum++;
                continue;
            } else {
                randomList.add(randomNum);
            }
        }

        // 4. 根据索引从数据库中获得面试题
        List<InitQuestionsVO> questionsList = new ArrayList<>();
        for (Long l : randomList) {
            Map<String, Object> map = new HashMap<>();
            map.put("indexNum", l);
            InitQuestionsVO question = questionLibMapperCustom.queryRandomQuestion(map);
            questionsList.add(question);
        }

        return questionsList;
    }

}
