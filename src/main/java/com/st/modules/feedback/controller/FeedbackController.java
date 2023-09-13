package com.st.modules.feedback.controller;

import com.st.common.api.CommonResult;
import com.st.modules.feedback.dto.AddFeedbackDto;
import com.st.modules.feedback.dto.UpdateFeedbackDto;
import com.st.modules.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/add")
    public CommonResult feedback(@RequestBody AddFeedbackDto addFeedbackDto) {
        feedbackService.addFeedback(addFeedbackDto);
        return CommonResult.success(null, "反馈成功");
    }

    @PostMapping("/update")
    public CommonResult updateFeedback(@RequestBody UpdateFeedbackDto updateFeedbackDto) {
        feedbackService.updateFeedback(updateFeedbackDto);
        return CommonResult.success(null, "修改反馈状态成功");
    }
}
