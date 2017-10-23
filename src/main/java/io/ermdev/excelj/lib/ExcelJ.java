package io.ermdev.excelj.lib;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import io.ermdev.excelj.annotation.Column;
import io.ermdev.excelj.annotation.Sheet;

public class ExcelJ {

	private static Logger logger = Logger.getLogger(ExcelJ.class.getSimpleName());
	
	public void register(Class<?> clazz) {
		final Sheet sheet = clazz.getAnnotation(Sheet.class);
		final List<String> columns = new ArrayList<>();
		String name = sheet.name().trim();

		for(Field field : clazz.getDeclaredFields()) {
			Column col = field.getAnnotation(Column.class);
			if(col != null) {
				columns.add(!col.name().trim().isEmpty() ?
						col.name() : field.getName().trim());
			}
		}

		if(name.trim().isEmpty())
			name = clazz.getSimpleName();

		DocumentHelper.createFile(name, sheet, columns);
		logger.info("Register complete");
	}
	
	public void save(List<?> list) {
		try {
			if(list.size() < 1) {
				logger.info("There no object to save");
				logger.info("Complete");
			} else {
				Class<?> clazz = list.get(0).getClass();
				Sheet sheet = clazz.getAnnotation(Sheet.class);

				final String name = !sheet.name().trim().isEmpty() ? sheet.name() : clazz.getSimpleName();
				final String dir = sheet.dir();
				final Version version = sheet.version();
				final String path = (dir.equals("") ? "" : dir.concat("/")) + name.concat(".") + version.val();
				final File file = new File(path);

				if (!file.exists())
					register(clazz);

				DocumentHelper.write(file, list);
				logger.info("Complete");
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void save(Object object) {
		try {
			if(object == null) {
				logger.info("There no object to save");
				logger.info("Complete");
			}else {
				final Class<?> clazz = object.getClass();
				Sheet sheet = clazz.getAnnotation(Sheet.class);

				final String name = !sheet.name().trim().isEmpty() ? sheet.name() : clazz.getSimpleName();
				final String dir = sheet.dir();
				final Version version = sheet.version();
				final String path = (dir.equals("") ? "" : dir.concat("/")) + name.concat(".") + version.val();
				final File file = new File(path);

				if (!file.exists())
					register(clazz);

				DocumentHelper.write(file, object);
				logger.info("Complete");
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
}
