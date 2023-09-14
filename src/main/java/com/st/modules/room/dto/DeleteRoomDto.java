package com.st.modules.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRoomDto {
    @NotBlank
    private Integer roomDbId;
}
