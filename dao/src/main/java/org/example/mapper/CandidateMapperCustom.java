package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.Candidate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.pojo.vo.CandidateVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 应聘者表 Mapper 接口
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
public interface CandidateMapperCustom extends BaseMapper<Candidate> {
    public List<CandidateVO> queryCandidateLibList(@Param("paramMap") Map<String, Object> map);
}
