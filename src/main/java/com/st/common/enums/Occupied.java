package com.st.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Occupied {
    EMPTY(0, "空闲"),
    OCCUPIED(1, "占用");

    @EnumValue
    @JsonValue
    private final int code;
    private final String description;

    Occupied(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    @JsonCreator
    public static Occupied getByValue(final int value) {
        for (final Occupied o : Occupied.values()) {
            if (o.code == value) {
                return o;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }
}
