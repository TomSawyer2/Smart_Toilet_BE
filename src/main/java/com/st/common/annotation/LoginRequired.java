package com.st.common.annotation;

import com.st.common.enums.Permission;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
    Permission[] needPermission();
}
