package com.test;

public class Student {
private int id;
private String name;
private String city;
private String mobNum;
private int score;
public Student() {
	super();
	
}
public Student(int id, String name, int score) {
	super();
	this.id = id;
	this.name = name;
	this.score = score;
}


public Student(String name, String city, String mobNum) {
	super();
	this.name = name;
	this.city = city;
	this.mobNum = mobNum;
}

public Student(int id, String name, String city, String mobNum) {
	super();
	this.id = id;
	this.name = name;
	this.city = city;
	this.mobNum = mobNum;
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
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getMobNum() {
	return mobNum;
}
public void setMobNum(String mobNum) {
	this.mobNum = mobNum;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}


}
