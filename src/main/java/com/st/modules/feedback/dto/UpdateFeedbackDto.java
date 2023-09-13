package com.st.modules.feedback.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateFeedbackDto {
    @NotBlank
    private Integer feedbackId;

    @NotBlank
    private Integer status;
}
