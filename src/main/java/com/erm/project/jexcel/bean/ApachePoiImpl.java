package com.erm.project.jexcel.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.erm.project.jexcel.annotation.Column;
import com.erm.project.jexcel.annotation.Sheet;

/**
 * 
 * @author erafaelmanuel
 *
 */
public class ApachePoiImpl {
	
	/**
	 * 
	 * @param name
	 * @param entity
	 * @param columns
	 */
	public void createFile(String name, Sheet entity, List<String> columns) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet(name);
	        
	        final File file = new File(name + ".xlsx");
	        
	        Row row = sheet.createRow(0);
	        int colNum = 0;
	        for(String col : columns) {
	        	Cell cell = row.createCell(colNum++);
                if (col instanceof String) {
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
	
	/**
	 * 
	 * @param file
	 * @param list
	 */
	public void write(final File file, final List<?> list) {
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
            workbook = null;
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
