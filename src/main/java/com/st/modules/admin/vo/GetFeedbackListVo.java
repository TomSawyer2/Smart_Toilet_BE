package com.st.modules.admin.vo;

import com.st.modules.feedback.model.Feedback;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GetFeedbackListVo {
    @NotBlank
    private Integer page;

    @NotBlank
    private Integer pageSize;

    @NotBlank
    private Integer total;

    @NotBlank
    private List<Feedback> list;
}
