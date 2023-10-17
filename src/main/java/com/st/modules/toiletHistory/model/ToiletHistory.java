package com.st.modules.toiletHistory.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("toilet_history")
public class ToiletHistory {
    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private Integer toiletId;

    private String name;

    private Float temperature;

    private Float humidity;

    private Float airStatus;

    private Integer fanStatus;

    private java.util.Date updateTime;
}
