package org.example.service.impl;

import org.example.pojo.Candidate;
import org.example.mapper.CandidateMapper;
import org.example.service.ICandidateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应聘者表 服务实现类
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Service
public class CandidateServiceImpl extends ServiceImpl<CandidateMapper, Candidate> implements ICandidateService {

}
