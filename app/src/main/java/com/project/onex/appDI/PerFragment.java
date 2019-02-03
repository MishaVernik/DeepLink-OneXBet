package com.project.onex.appDI;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Named;
import javax.inject.Scope;

@Scope
@Named("SingleThread")
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {}