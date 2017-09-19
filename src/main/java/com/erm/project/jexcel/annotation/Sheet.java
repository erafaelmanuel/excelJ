package com.erm.project.jexcel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.erm.project.jexcel.model.Version;

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
	 * The file location of the SpreadSheet
	 * Cannot be named with special character except (_, -, / and .)
	 * @return can be empty
	 */

	String location() default "";
	
	/**
	 * 
	 * @return
	 */

	Version version() default Version.XLXS;
	
	/**
	 * 
	 * @return
	 */

	int colNum() default 0;
}
