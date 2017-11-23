package io.ermdev.excelj.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, PARAMETER})
@Retention(RUNTIME)
public @interface Style {

    Scope scope() default Scope.TITLE;


    enum Scope {
        ALL, TITLE, FIELD;
    }
}
