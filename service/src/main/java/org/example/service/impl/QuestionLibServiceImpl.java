package org.example.service.impl;

import org.example.pojo.QuestionLib;
import org.example.mapper.QuestionLibMapper;
import org.example.service.IQuestionLibService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
