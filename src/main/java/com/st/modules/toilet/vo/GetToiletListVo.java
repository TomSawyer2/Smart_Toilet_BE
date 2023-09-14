package com.st.modules.toilet.vo;

import com.st.modules.room.model.Room;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GetToiletListVo {
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

    @NotBlank
    private java.util.Date updateTime;

    private List<Room> roomList;
}
