package org.example.service;

import org.example.pojo.QuestionLib;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.bo.QuestionLibBO;
import org.example.pojo.vo.InitQuestionsVO;
import org.example.utils.PagedGridResult;

import java.util.List;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题） 服务类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public interface IQuestionLibService {

    public void createOrUpdate(QuestionLibBO questionLibBO);

    public PagedGridResult queryList(String aiName, String question, Integer page, Integer pageSize);

    /**
     * 设置是否开启面试题对外显示
     * @param questionLibId
     * @param isOn
     */
    public void setDisplayOrNot(String questionLibId, Integer isOn);

    public void delete(String questionLibId);

    // 判断所有面试题库中是否包含某个面试官
    public boolean isQuestionLibContainInterviewer(String interviewerId);

    // 获得指定随机数量的面试题
    public List<InitQuestionsVO> getRandomQuestions(String candidateId, Integer questionNum);
}
