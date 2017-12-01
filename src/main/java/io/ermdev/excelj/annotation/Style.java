package io.ermdev.excelj.annotation;

import org.apache.poi.hssf.util.HSSFColor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, PARAMETER})
@Retention(RUNTIME)
public @interface Style {

    Scope scope() default Scope.TITLE;
    short color() default HSSFColor.BLACK.index;


    enum Scope {
        ALL, TITLE, FIELD;
    }
}
