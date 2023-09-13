package com.st.modules.device.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("device_data")
public class Device {
    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private String sn;

    private String value;

    private java.util.Date updateTime;
}
