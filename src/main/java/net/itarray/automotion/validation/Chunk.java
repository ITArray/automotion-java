package net.itarray.automotion.validation;

import net.itarray.automotion.validation.Element;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Chunk {
    Element[] value() default {};
    String name() default "";
    String[] params() default {""};
    boolean oneOrMore() default false;
}
