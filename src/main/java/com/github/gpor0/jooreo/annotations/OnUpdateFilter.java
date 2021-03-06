package com.github.gpor0.jooreo.annotations;

import com.github.gpor0.jooreo.JooreoRecordFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: gpor0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OnUpdateFilter {

    Class<? extends JooreoRecordFilter> value();

}
