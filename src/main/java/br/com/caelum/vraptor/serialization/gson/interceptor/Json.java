package br.com.caelum.vraptor.serialization.gson.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.caelum.vraptor.ioc.Stereotype;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Stereotype
public @interface Json {
    String[] exclude() default{};
}
