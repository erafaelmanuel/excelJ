package io.ermdev.excelj.core;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import io.ermdev.excelj.annotation.Column;
import io.ermdev.excelj.annotation.Sheet;
import io.ermdev.excelj.config.AppConfig;
import io.ermdev.excelj.exception.UnEnabledToParseException;

public class ExcelJ {

	private static Logger logger = Logger.getLogger(ExcelJ.class.getSimpleName());

	/**
	 * Register does is to make an empty file for the classes annotated of @Sheet
	 * and it's optional, then if file is not exists 'Save' method calls it automatically.
	 *
	 * @param clazz
	 * @throws UnEnabledToParseException if the class does not annotated with @Sheet
	 * @return the file name
	 */
	public String register(Class<?> clazz) throws UnEnabledToParseException {
		if(clazz.getAnnotation(Sheet.class) == null)
			throw new UnEnabledToParseException("The class is not a sheet");

		final Sheet sheet = clazz.getAnnotation(Sheet.class);
		final List<String> columns = new ArrayList<>();
		final String name = !sheet.name().trim().isEmpty() ? sheet.name() : clazz.getSimpleName();
		final String fileName = (sheet.dir().equals("") ? "" : sheet.dir().concat("/")) + name.concat(".") +
				sheet.version().toString();

		//set the columns name
		for(Field field : clazz.getDeclaredFields()) {
			Column col = field.getAnnotation(Column.class);
			if(col != null) {
				columns.add(!col.name().trim().isEmpty() ? col.name() : field.getName().trim());
			}
		}

		//create the actual file
		DocumentHelper.createFile(name, fileName, columns);

		if(AppConfig.SHOW_LOG) {
			logger.info("Register complete");
		}
		return fileName;
	}
	
	public void save(List<?> list) throws UnEnabledToParseException{
		try {
			if(list.size() < 1) {
				if(AppConfig.SHOW_LOG) {
					logger.info("There no object to save");
					logger.info("Complete");
				}
			} else {
				final Class<?> clazz = list.get(0).getClass();
				final File file = new File(register(clazz));

				DocumentHelper.write(file, list);

				if(AppConfig.SHOW_LOG) {
					logger.info("Complete");
				}
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void save(Object object) throws UnEnabledToParseException{
		try {
			if(object == null) {
				if(AppConfig.SHOW_LOG) {
					logger.info("There no object to save");
					logger.info("Complete");
				}
			}else {
				final Class<?> clazz = object.getClass();
				final File file = new File(register(clazz));

				DocumentHelper.write(file, object);

				if(AppConfig.SHOW_LOG) {
					logger.info("Complete");
				}
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
}
