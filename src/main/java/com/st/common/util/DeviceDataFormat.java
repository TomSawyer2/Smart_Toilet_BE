package com.st.common.util;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeviceDataFormat {
    @NotBlank
    private Integer id;

    @NotBlank
    private Long sn;

    @NotBlank
    private String value;

    @NotBlank
    private String updateTime;
}
