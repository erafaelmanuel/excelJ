package io.ermdev.excelj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.ermdev.excelj.lib.Version;

/**
 * 
 * @author erafaelmanuel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
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
