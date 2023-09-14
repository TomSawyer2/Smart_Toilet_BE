package com.st.modules.roomHistory.vo;

import com.st.modules.roomHistory.model.RoomHistory;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GetRoomHistoryVo {
    @NotBlank
    private Integer page;

    @NotBlank
    private Integer pageSize;

    @NotBlank
    private Integer total;

    private List<RoomHistory> list;
}
