package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.vo.JobVO;

import java.util.List;
import java.util.Map;

public interface JobMapperCustom {

    public List<JobVO> queryJobList(@Param("paramMap") Map<String, Object> map);
}
