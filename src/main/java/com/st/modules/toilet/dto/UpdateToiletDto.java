package com.st.modules.toilet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateToiletDto {
    @NotBlank
    private Integer id;

    @NotBlank
    private String name;

    private Float temperature;

    private Float humidity;

    private Float airStatus;

    private String tempSensorSn;

    private String airSensorSn;

    private String fanSn;

    private String loraSn;
}
