package com.erm.project.jexcel.test;

import com.erm.project.jexcel.annotation.Column;
import com.erm.project.jexcel.annotation.Sheet;
import com.erm.project.jexcel.model.Version;

@Sheet(name="dog", version=Version.XLXS)
public class Dog {
	
	public Dog() {
		super();
	}
	
	@Column(name = "Fullname")
	private String name;
	
	@Column(name = "Age")
	private int age;
	
	private String color;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
