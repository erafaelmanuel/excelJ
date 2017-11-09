package io.ermdev.excelj.core;

import io.ermdev.excelj.annotation.Column;
import io.ermdev.excelj.annotation.Sheet;
import io.ermdev.excelj.config.AppConfig;
import io.ermdev.excelj.exception.UnEnabledToParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

    public <T> T get(Class<T> c, int rowNum) throws UnEnabledToParseException {
        try {
            if(rowNum < 1)
                return null;
            if(c.getAnnotation(Sheet.class) == null)
                throw new UnEnabledToParseException("The class is not a sheet");
            final Sheet sheet = c.getAnnotation(Sheet.class);
            final T instance = c.newInstance();

            final String name = !sheet.name().trim().isEmpty() ? sheet.name() : c.getSimpleName();
            final String fileName = (sheet.dir().equals("") ? "" : sheet.dir().concat("/")) + name.concat(".") +
                    sheet.version().toString();

            if(!DocumentHelper.isExist(fileName)) {
                //create a file
                register(c);
            }
            XSSFSheet doc = DocumentHelper.readFile(name, fileName);
            if(rowNum > doc.getLastRowNum())
                return null;

            int index = 0;
            for (Field field : instance.getClass().getDeclaredFields()) {
                if(field.getAnnotation(Column.class) == null) continue;

                field.setAccessible(true);
                String value = "";
                if(doc.getRow(rowNum).getCell(index).getCellType()== Cell.CELL_TYPE_NUMERIC)
                    value += doc.getRow(rowNum).getCell(index).getNumericCellValue();
                else
                    value += doc.getRow(rowNum).getCell(index).getStringCellValue();

                if (field.getType().toString().startsWith("class"))
                    castToReference(instance, field, value);
                else
                    castToPrimitive(instance, field, value);
                index++;
            }

            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> load(Class<T> c) throws UnEnabledToParseException {
        try {
            if(c.getAnnotation(Sheet.class) == null)
                throw new UnEnabledToParseException("The class is not a sheet");
            final List<T> list = new ArrayList<>();
            final Sheet sheet = c.getAnnotation(Sheet.class);

            final String name = !sheet.name().trim().isEmpty() ? sheet.name() : c.getSimpleName();
            final String fileName = (sheet.dir().equals("") ? "" : sheet.dir().concat("/")) + name.concat(".") +
                    sheet.version().toString();

            if(!DocumentHelper.isExist(fileName)) {
                //create a file
                register(c);
            }

            XSSFSheet doc = DocumentHelper.readFile(name, fileName);
            for(int i=1; i<=doc.getLastRowNum(); i++) {
                final T instance = c.newInstance();
                int index = 0;
                for (Field field : instance.getClass().getDeclaredFields()) {
                    if(field.getAnnotation(Column.class) == null) continue;

                    field.setAccessible(true);
                    String value = "";
                    if(doc.getRow(i).getCell(index).getCellType()== Cell.CELL_TYPE_NUMERIC)
                        value += doc.getRow(i).getCell(index).getNumericCellValue();
                    else
                        value += doc.getRow(i).getCell(index).getStringCellValue();

                    if (field.getType().toString().startsWith("class"))
                        castToReference(instance, field, value);
                    else
                        castToPrimitive(instance, field, value);
                    index++;
                }
                list.add(instance);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> T cast(Class<T> c, String value) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        try {
            if (c.equals(String.class))
                return c.getConstructor(c).newInstance(value);
            else if (c.equals(Integer.class))
                return c.getConstructor(int.class).newInstance(Integer.parseInt(value));
            else if (c.equals(Long.class))
                return c.getConstructor(long.class).newInstance(Long.parseLong(value));
            else if (c.equals(Float.class))
                return c.getConstructor(float.class).newInstance(Float.parseFloat(value));
            else if (c.equals(Double.class))
                return c.getConstructor(double.class).newInstance(Double.parseDouble(value));
            else if (c.equals(Boolean.class))
                return c.getConstructor(boolean.class).newInstance(Boolean.parseBoolean(value));
            else if (c.equals(Character.class))
                return c.getConstructor(char.class).newInstance(value.toCharArray()[0]);
            return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void castToReference(Object o, Field field, String value) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        try {
            final Class<?> c = field.getType();
            if (c.equals(String.class))
                field.set(o, c.getConstructor(c).newInstance(value));
            else if (c.equals(Integer.class)) {
                if(value.split("\\.").length > 0) {
                    value = value.split("\\.")[0];
                }
                field.set(o, c.getConstructor(int.class).newInstance(Integer.parseInt(value)));
            } else if (c.equals(Long.class)) {
                if(value.split("\\.").length > 0) {
                    value = value.split("\\.")[0];
                }
                field.set(o, c.getConstructor(long.class).newInstance(Long.parseLong(value)));
            } else if (c.equals(Float.class))
                field.set(o, c.getConstructor(float.class).newInstance(Float.parseFloat(value)));
            else if (c.equals(Double.class))
                field.set(o, c.getConstructor(double.class).newInstance(Double.parseDouble(value)));
            else if (c.equals(Boolean.class))
                field.set(o, c.getConstructor(boolean.class).newInstance(Boolean.parseBoolean(value)));
            else if (c.equals(Character.class))
                field.set(o, c.getConstructor(char.class).newInstance(value.toCharArray()[0]));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void castToPrimitive(Object o, Field field, String value) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        try {
            if (field.getType().equals(int.class)) {
                if(value.split("\\.").length > 0) {
                    value = value.split("\\.")[0];
                }
                field.setInt(o, Integer.parseInt(value));
            } if (field.getType().equals(long.class)) {
                if(value.split("\\.").length > 0) {
                    value = value.split("\\.")[0];
                }
                field.setLong(o, Long.parseLong(value));
            } if (field.getType().equals(float.class))
                field.setFloat(o, Float.parseFloat(value));
            if (field.getType().equals(double.class))
                field.setDouble(o, Double.parseDouble(value));
            if (field.getType().equals(boolean.class))
                field.setBoolean(o, Boolean.parseBoolean(value));
            if (field.getType().equals(char.class))
                field.setChar(o, value.toCharArray()[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
    }
}
