package org.example.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.enums.YesOrNo;
import org.example.pojo.QuestionLib;
import org.example.mapper.QuestionLibMapper;
import org.example.pojo.bo.QuestionLibBO;
import org.example.service.IQuestionLibService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题） 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class QuestionLibServiceImpl extends ServiceImpl<QuestionLibMapper, QuestionLib> implements IQuestionLibService {

    @Resource
    private QuestionLibMapper questionLibMapper;

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
}
