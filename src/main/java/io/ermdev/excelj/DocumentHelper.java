package io.ermdev.excelj;

import io.ermdev.excelj.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class DocumentHelper {

	private static XSSFFont titleFont(XSSFWorkbook workbook) {
		XSSFFont font= workbook.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("Arial");
		font.setBold(true);
		font.setItalic(false);
		return font;
	}

	private static XSSFFont defaultFont(XSSFWorkbook workbook) {
		XSSFFont font=workbook.createFont();
		font.setFontHeightInPoints((short)11);
		font.setFontName("Arial");
		font.setBold(false);
		font.setItalic(false);
		return font;
	}

	public static void createFile(String name, String fileName, List<String> columns) {
		try {
			new File(fileName.split(name)[0]).mkdirs();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		try {
			final XSSFWorkbook workbook = new XSSFWorkbook();
	        final XSSFSheet sheet = workbook.createSheet(name);
	        final File file = new File(fileName);
	        
	        Row row = sheet.createRow(0);
	        int colNum = 0;

			CellStyle style = workbook.createCellStyle();
			style.setFont(titleFont(workbook));
	        for(String col : columns) {
	        	Cell cell = row.createCell(colNum++);
                if (col != null) {
                    cell.setCellValue(col);
                    cell.setCellStyle(style);
                }
	        }

	        FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);

            fos.flush();
            fos.close();

		}catch(IOException e) {
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
	                //cell.getCellStyle().setFont(defaultFont(workbook));
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
				//cell.getCellStyle().setFont(defaultFont(workbook));
	        }
	        
	        FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static XSSFSheet readFile(String name, String fileName) {
		try {
			final File file = new File(fileName);

			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheetAt(0);

			fis.close();
			return sheet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isExist(String location) {
		return new File(location).exists();
	}
}
