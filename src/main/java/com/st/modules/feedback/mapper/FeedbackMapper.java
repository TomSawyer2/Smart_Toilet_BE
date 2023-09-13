package com.st.modules.feedback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.st.modules.feedback.model.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FeedbackMapper extends BaseMapper<Feedback> {
}
