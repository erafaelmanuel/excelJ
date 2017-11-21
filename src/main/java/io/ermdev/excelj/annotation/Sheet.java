package io.ermdev.excelj.annotation;

import io.ermdev.excelj.core.Version;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author erafaelmanuel
 *
 */

@Target(TYPE)
@Retention(RUNTIME)
public @interface Sheet {

	/**
	 * The file name of the SpreadSheet
	 * Cannot be named with special character except (_, - and .)
	 * @return if empty return the Class simple name
	 */
	String name() default "";
	
	/**
	 * The file directory of SpreadSheet
	 * Cannot be named with special character except (_, -, / and .)
	 * @return can be empty
	 */

	String dir() default "";
	
	/**
	 * version of the file
	 * @return
	 */

	Version version() default Version.XLSX;
	
	/**
	 * Number of column
	 * @return
	 */

	int colNum() default 0;
}
