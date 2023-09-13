package com.st.modules.room.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("room")
public class Room {
    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private Integer roomId;

    private Integer toiletId;

    private Integer occupied;

    private Integer status;

    private String occupiedSensorSn;

    private java.util.Date updateTime;
}
