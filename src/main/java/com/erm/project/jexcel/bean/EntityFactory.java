package com.erm.project.jexcel.bean;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.erm.project.jexcel.annotation.Column;
import com.erm.project.jexcel.annotation.Sheet;

public class EntityFactory {
	
	public void register(Class<?> clazz) {

			final Sheet entity = clazz.getAnnotation(Sheet.class);
			final List<String> columns = new ArrayList<>();
			String name = entity.name().trim();

			for(Field field : clazz.getDeclaredFields()) {
				Column col = field.getAnnotation(Column.class);
				if(col != null) {
					columns.add(!col.name().trim().isEmpty() ? 
							col.name() : field.getName().trim());
				}
			}
			
			if(name.trim().isEmpty())
				name = clazz.getSimpleName();
			
			ApachePoiImpl helper = new ApachePoiImpl();
			helper.createFile(name, entity, columns);	
	}
	
	public void save(List<?> list) {
		try {
			if(list.size() < 1)
				return;
			Class<?> clazz = list.get(0).getClass();
			Sheet entity = clazz.getAnnotation(Sheet.class);

			String name = !entity.name().trim().isEmpty() ? entity.name().trim() : clazz.getSimpleName();
			File file = new File(name + ".xlsx");

			if(!file.exists())
				register(clazz);

			ApachePoiImpl helper = new ApachePoiImpl();
			helper.write(file, list);
			
			System.out.println("Complete");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
