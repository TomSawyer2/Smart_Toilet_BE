package com.st.modules.deviceData.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("device_data")
public class DeviceData {
    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private String sn;

    private String value;

    private java.util.Date updateTime;
}
