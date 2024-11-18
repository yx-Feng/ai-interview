package org.example.service;

import org.example.pojo.QuestionLib;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.bo.QuestionLibBO;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题） 服务类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public interface IQuestionLibService extends IService<QuestionLib> {

    public void createOrUpdate(QuestionLibBO questionLibBO);
}
