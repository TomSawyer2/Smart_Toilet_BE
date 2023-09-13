package com.st.modules.feedback.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddFeedbackDto {
    @NotBlank
    private Integer toiletId;

    @NotBlank
    private Integer roomId;

    @NotBlank
    private Integer roomDbId;

    @NotBlank
    private String content;
}
