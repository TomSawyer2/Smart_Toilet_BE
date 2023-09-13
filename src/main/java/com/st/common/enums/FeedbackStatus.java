package com.st.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FeedbackStatus {
    UNPROCESSED(0, "未处理"),
    PROCESSED(1, "已处理");

    @EnumValue
    @JsonValue
    private final int code;
    private final String description;

    FeedbackStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    @JsonCreator
    public static FeedbackStatus getByValue(final int value) {
        for (final FeedbackStatus f : FeedbackStatus.values()) {
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
