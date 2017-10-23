package io.ermdev.excelj.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import io.ermdev.excelj.annotation.Column;
import io.ermdev.excelj.annotation.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DocumentHelper {

	public static void createFile(String name, Sheet entity, List<String> columns) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet(name);
	        
	        final File file = new File(name + ".xlsx");
	        
	        Row row = sheet.createRow(0);
	        int colNum = 0;
	        for(String col : columns) {
	        	Cell cell = row.createCell(colNum++);
                if (col != null) {
                    cell.setCellValue((String) col);
                }
	        }
	       
	        FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            workbook = null;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void write(final File file, final List<?> list) {
		try {
	        FileInputStream fis = new FileInputStream(file);
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        for(int i=0; i<list.size(); i++) {
		        Row row = sheet.createRow(i+1);
		        int colNum = 0;
		        for(Field field : list.get(i).getClass().getDeclaredFields()) {
		        	
		        	//Make the private variable accessible
		        	field.setAccessible(true);
		        	
		        	Object object = field.get(list.get(i));
		        	Column column = field.getAnnotation(Column.class);
		        	
		        	if(column == null)
		        		continue;

		        	Cell cell = row.createCell(colNum++);
		        	
		        	if (object instanceof String) {
	                    cell.setCellValue((String) object);
	                }
		        	
		        	if (object instanceof Integer) {
	                    cell.setCellValue((int) object);
	                }
		        }
	        }
	        
	        FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static <T> void write(final File file, final T t) {
		try {
	        FileInputStream fis = new FileInputStream(file);
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheetAt(0);

	        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
	        int colNum = 0;
	        for(Field field : t.getClass().getDeclaredFields()) {
	        	
	        	//Make the private variable accessible
	        	field.setAccessible(true);
	        	
	        	Object object = field.get(t);
	        	Column column = field.getAnnotation(Column.class);
	        	
	        	if(column == null)
	        		continue;

	        	Cell cell = row.createCell(colNum++);
	        	
	        	if (object instanceof String) {
                    cell.setCellValue((String) object);
                }
	        	
	        	if (object instanceof Integer) {
                    cell.setCellValue((int) object);
                }
	        }
	        
	        FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
