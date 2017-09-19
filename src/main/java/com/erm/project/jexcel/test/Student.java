package com.erm.project.jexcel.test;

import com.erm.project.jexcel.annotation.Column;
import com.erm.project.jexcel.annotation.Sheet;

@Sheet(name = "filename")
public class Student {
	
	@Column(name = "studentId")
	private int id;
	
	@Column(name = "Full Name")
	private String name;
	
	@Column(name = "Age")
	private int age;

	public Student(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
}
