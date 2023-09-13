package com.st.modules.feedback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.st.common.api.ResultCode;
import com.st.common.exception.Asserts;
import com.st.modules.feedback.dto.AddFeedbackDto;
import com.st.modules.feedback.dto.UpdateFeedbackDto;
import com.st.modules.feedback.mapper.FeedbackMapper;
import com.st.modules.feedback.model.Feedback;
import com.st.modules.feedback.service.FeedbackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public void addFeedback(AddFeedbackDto addFeedbackDto) {
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(addFeedbackDto, feedback);
        feedback.setUpdateTime(new Date());
        feedbackMapper.insert(feedback);
    }

    @Override
    public void updateFeedback(UpdateFeedbackDto updateFeedbackDto) {
        // 先从数据库中查出来，再更新
        Feedback feedback = feedbackMapper.selectById(updateFeedbackDto.getFeedbackId());
        if (feedback == null) {
            Asserts.fail(ResultCode.FEEDBACK_NOT_EXIST);
        } else {
            feedback.setStatus(updateFeedbackDto.getStatus());
            feedbackMapper.updateById(feedback);
        }
    }
}
