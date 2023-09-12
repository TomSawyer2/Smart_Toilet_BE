package com.st.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Permission {
    USER(0, "普通用户"),
    ADMIN(1, "管理员"),
    SUPER_ADMIN(2, "超级管理员");

    @EnumValue
    @JsonValue
    private final int code;
    private final String description;

    Permission(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    @JsonCreator
    public static Permission getByValue(final int value) {
        for (final Permission p : Permission.values()) {
            if (p.code == value) {
                return p;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }
}
