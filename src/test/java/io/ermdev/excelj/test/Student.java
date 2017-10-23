package io.ermdev.excelj.test;

import io.ermdev.excelj.annotation.Column;
import io.ermdev.excelj.annotation.Sheet;

@Sheet
public class Student {
	
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
	private int age;

	public Student(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
}
