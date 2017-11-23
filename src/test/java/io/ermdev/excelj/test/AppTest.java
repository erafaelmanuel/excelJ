package io.ermdev.excelj.test;

import io.ermdev.excelj.core.ExcelJ;
import io.ermdev.excelj.core.Initializer;
import io.ermdev.excelj.exception.UnEnabledToParseException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest {

    private final List<Dog> dogs = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    private final ExcelJ factory = new ExcelJ();

    @Before
    public void setup() {
        Initializer.hideLogs();

        Student student1 = new Student(324324, "Verlie", 19);
        Student student2 = new Student(435435, "Ronald", 15);

        students.add(student1);
        students.add(student2);

        Dog dog1 = new Dog();
        dog1.setName("Tony");
        dog1.setAge(2);
        dog1.setColor("brown");

        Dog dog2 = new Dog();
        dog2.setName("Michael");
        dog2.setAge(4);
        dog2.setColor("black");

        dogs.addAll(Arrays.asList(dog1, dog2));

        customers.add(new Customer("Kelvin Datu"));
    }

    @Ignore
    @Test
    public void load() {
        try {
            Zombie zombie1 = new Zombie();
            zombie1.isAlive = 1546;
            zombie1.name = "Ronald";

            Zombie zombie2 = new Zombie();
            zombie2.isAlive = 345435;
            zombie2.name = "Verlie";

            factory.save(Arrays.asList(zombie1, zombie2));

            factory.save(students);

            //List<Zombie> zombies = new ExcelJ().load(Zombie.class);
            System.out.println(new ExcelJ().load(Student.class));

        } catch (UnEnabledToParseException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void singleDog() {
        try {
            factory.save(dogs.get(0));
        } catch (UnEnabledToParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void multipleDogs() {
        try {
            factory.save(dogs);
        } catch (UnEnabledToParseException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void singleStudent() {
        try {
            factory.save(students.get(0));
        } catch (UnEnabledToParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void multipleStudents() {
        try {
            factory.save(students);
        } catch (UnEnabledToParseException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void registerACustomer() {
        try {
            factory.save(customers.get(0));
        } catch (UnEnabledToParseException e) {
            e.printStackTrace();
        }
    }
}
