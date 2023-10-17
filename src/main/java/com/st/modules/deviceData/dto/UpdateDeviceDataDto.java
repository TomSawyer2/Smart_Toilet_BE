package com.st.modules.deviceData.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateDeviceDataDto {
    @NotBlank
    private String sn;

    @NotBlank
    private String value;
}
