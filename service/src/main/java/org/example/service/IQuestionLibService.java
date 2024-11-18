package org.example.service;

import org.example.pojo.QuestionLib;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.bo.QuestionLibBO;
import org.example.utils.PagedGridResult;

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
}
