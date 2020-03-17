package com.gqs.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "company")
public class Company {

	private int id;
	private String name;
	private List<Dept> dept;

	@XmlAttribute(name = "id")
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

	@XmlElementWrapper(name = "depts")
	@XmlElement(name = "dept")
	public List<Dept> getDept() {
		return dept;
	}

	public void setDept(List<Dept> dept) {
		this.dept = dept;
	}

}
