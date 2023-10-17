package com.st.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FanStatus {
    SHUTDOWN(0, "关闭"),
    RUNNING(1, "运行中");

    @EnumValue
    @JsonValue
    private final int code;
    private final String description;

    FanStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    @JsonCreator
    public static FanStatus getByValue(final int value) {
        for (final FanStatus f : FanStatus.values()) {
            if (f.code == value) {
                return f;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }
}
