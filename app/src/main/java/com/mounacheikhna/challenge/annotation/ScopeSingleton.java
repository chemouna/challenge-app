package com.mounacheikhna.challenge.annotation;

import javax.inject.Scope;

@Scope public @interface ScopeSingleton {
    Class<?> value();
}
