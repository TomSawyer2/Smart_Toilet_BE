package com.st.modules.roomHistory.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("room_history")
public class RoomHistory {
    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private Integer roomId;

    private Integer toiletId;

    private Integer occupied;

    private Integer status;

    private java.util.Date updateTime;
}
