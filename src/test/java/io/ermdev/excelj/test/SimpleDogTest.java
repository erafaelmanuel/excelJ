package io.ermdev.excelj.test;

import io.ermdev.excelj.lib.ExcelJ;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleDogTest {

    private final List<Dog> dogList = new ArrayList<>();
    private final ExcelJ factory = new ExcelJ();

    @Before
    public void beforeTest() {
        Dog dog1 = new Dog();
        dog1.setName("Tony");
        dog1.setAge(2);
        dog1.setColor("brown");

        Dog dog2 = new Dog();
        dog2.setName("Michael");
        dog2.setAge(4);
        dog2.setColor("black");

        dogList.addAll(Arrays.asList(dog1, dog2));
    }

    @Test
    public void singleObject() {
        factory.save(dogList.get(0));
    }

    @Test
    public void multipleObject() {
        factory.save(dogList);
    }
}
