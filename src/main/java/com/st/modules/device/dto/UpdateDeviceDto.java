package com.st.modules.device.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateDeviceDto {
    @NotBlank
    private String sn;

    @NotBlank
    private String value;
}
