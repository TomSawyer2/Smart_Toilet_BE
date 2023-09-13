package com.st.modules.toilet.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("toilet")
public class Toilet {
    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private String name;

    private Float temperature;

    private Float humidity;

    private Float airStatus;

    private String tempSensorSn;

    private String airSensorSn;

    private String fanSn;

    private String loraSn;

    private java.util.Date updateTime;
}
