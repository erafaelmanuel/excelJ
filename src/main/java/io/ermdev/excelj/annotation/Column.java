package io.ermdev.excelj.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author erafaelmanuel
 */

@Target(FIELD)
@Retention(RUNTIME)
public @interface Column {
	
	/**
	 * Name or title of the column
	 * @return if empty, it will be replace with the name of the variable name
	 *
	 */

	String name() default "[FIELD_NAME]";
	Style style() default @Style;
}
