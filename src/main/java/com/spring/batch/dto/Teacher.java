package com.spring.batch.dto;

import java.io.Serializable;

public class Teacher  implements Serializable
 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5857842076779536181L;
	private int id;
    private String name;
    private int salary;
    private int salaryEarned;
    private int studId;
    
	public int getStudId() {
		return studId;
	}
	public void setStudId(int studId) {
		this.studId = studId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getSalaryEarned() {
		return salaryEarned;
	}
	public void setSalaryEarned(int salaryEarned) {
		this.salaryEarned = salaryEarned;
	}
    
    
}
