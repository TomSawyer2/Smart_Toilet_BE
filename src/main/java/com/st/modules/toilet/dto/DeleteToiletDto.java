package com.st.modules.toilet.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteToiletDto {
    @NotBlank
    private Integer toiletId;
}
