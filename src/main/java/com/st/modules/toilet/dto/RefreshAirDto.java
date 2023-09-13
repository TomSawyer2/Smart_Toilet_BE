package com.st.modules.toilet.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshAirDto {
    @NotBlank
    private Integer toiletId;
}
