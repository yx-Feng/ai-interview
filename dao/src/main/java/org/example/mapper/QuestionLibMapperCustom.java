package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.vo.InitQuestionsVO;
import org.example.pojo.vo.QuestionLibVO;

import java.util.List;
import java.util.Map;

public interface QuestionLibMapperCustom {
    public List<QuestionLibVO> queryQuestionLibList(@Param("paramMap") Map<String, Object> map);

    public InitQuestionsVO queryRandomQuestion(@Param("paramMap") Map<String, Object> map);
}
