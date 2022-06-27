package com.alpha.lab.interview.audit;

import ru.bellintegrator.autocode.audit.enumeration.EventTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Audited {
    EventTypeEnum event();
}
