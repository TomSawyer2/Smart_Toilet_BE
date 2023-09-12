package com.st.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoomStatus {
    NORMAL(0, "正常"),
    REPORTED(1, "已报修"),
    REPAIRING(2, "维修中");

    @EnumValue
    @JsonValue
    private final int code;
    private final String description;

    RoomStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    @JsonCreator
    public static RoomStatus getByValue(final int value) {
        for (final RoomStatus r : RoomStatus.values()) {
            if (r.code == value) {
                return r;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }
}
