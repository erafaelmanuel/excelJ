package io.ermdev.excelj.test;

import io.ermdev.excelj.lib.ExcelJ;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleStudentTest {

    private final List<Student> studentList = new ArrayList<>();
    private final ExcelJ factory = new ExcelJ();

    @Before
    public void beforeTest() {
        Student student1 = new Student(324324, "Verlie", 19);
        Student student2 = new Student(435435, "Ronald", 15);

        studentList.add(student1);
        studentList.add(student2);
    }

    @Test
    public void singleObject() {
        factory.save(studentList.get(0));
    }

    @Test
    public void multipleObject() {
        factory.save(studentList);
    }
}
