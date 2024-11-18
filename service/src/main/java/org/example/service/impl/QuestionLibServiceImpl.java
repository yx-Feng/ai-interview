package org.example.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.enums.YesOrNo;
import org.example.mapper.QuestionLibMapperCustom;
import org.example.pojo.QuestionLib;
import org.example.mapper.QuestionLibMapper;
import org.example.pojo.bo.QuestionLibBO;
import org.example.pojo.vo.QuestionLibVO;
import org.example.service.IQuestionLibService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.utils.PagedGridResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
