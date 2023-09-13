package com.st.modules.feedback.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.st.modules.feedback.dto.AddFeedbackDto;
import com.st.modules.feedback.dto.UpdateFeedbackDto;
import com.st.modules.feedback.model.Feedback;

public interface FeedbackService extends IService<Feedback> {
    void addFeedback(AddFeedbackDto addFeedbackDto);
    void updateFeedback(UpdateFeedbackDto updateFeedbackDto);
}
