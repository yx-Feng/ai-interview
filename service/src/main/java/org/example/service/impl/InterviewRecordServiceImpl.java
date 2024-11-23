package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.base.BaseInfoProperties;
import org.example.mapper.InterviewRecordMapperCustom;
import org.example.pojo.InterviewRecord;
import org.example.mapper.InterviewRecordMapper;
import org.example.pojo.vo.InterviewRecordVO;
import org.example.service.IInterviewRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.utils.PagedGridResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 面试记录表 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class InterviewRecordServiceImpl extends BaseInfoProperties implements IInterviewRecordService {

    @Resource
    private InterviewRecordMapper interviewRecordMapper;

    @Resource
    private InterviewRecordMapperCustom interviewRecordMapperCustom;

    @Override
    public void save(InterviewRecord interviewRecord) {
        interviewRecordMapper.insert(interviewRecord);
    }

    @Override
    public boolean isCandidateRecordExist(String candidateId) {
        List<InterviewRecord> list = interviewRecordMapper.selectList(new QueryWrapper<InterviewRecord>().eq("candidate_id", candidateId));
        if(list.isEmpty() || list.size() == 0) return false;
        return true;
    }

    @Override
    public PagedGridResult queryList(String realName, String mobile, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(realName)) {
            map.put("realName", realName);
        }
        if(StringUtils.isNotBlank(mobile)) {
            map.put("mobile", mobile);
        }
        List<InterviewRecordVO> list = interviewRecordMapperCustom.queryInterviewRecordList(map);
        return setterPagedGrid(list, page);
    }
}
