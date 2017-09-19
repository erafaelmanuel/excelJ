package com.erm.project.jexcel.test;

import java.util.ArrayList;
import java.util.List;

import com.erm.project.jexcel.bean.EntityFactory;

public class Test {
	
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		EntityFactory factory = new EntityFactory();
		
		List<Student> studentList = new ArrayList<>();
		Student student1 = new Student(324324, "Verlie", 19);
		Student student2 = new Student(435435, "Ronald", 15);
		
		studentList.add(student1);
		studentList.add(student2);
		
		factory.save(studentList);
	
	}

}
