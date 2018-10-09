package com.nivedita.realestate.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Defines scope for individual activity or fragment
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
