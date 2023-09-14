package com.st.modules.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePermissionDto {
    @NotBlank
    private Integer userId;

    @NotBlank
    private Integer permission;
}
