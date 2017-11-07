package io.ermdev.excelj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author erafaelmanuel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	
	/**
	 * Name or title of the column
	 * @return if empty, it will be replace with the name of the variable name
	 *
	 */

	String name() default "";
}
