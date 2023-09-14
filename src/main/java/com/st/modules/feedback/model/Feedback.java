package com.st.modules.feedback.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("feedback")
public class Feedback {
    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private Integer toiletId;

    private Integer roomId;

    private Integer roomDbId;

    private String content;

    private java.util.Date updateTime;

    private Integer status;
}
