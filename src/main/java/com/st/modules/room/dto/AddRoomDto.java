package com.st.modules.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRoomDto {
    @NotBlank
    private Integer roomId;

    @NotBlank
    private Integer toiletId;

    private String occupiedSensorSn;
}
