package com.gqs.bean;

import java.util.Date;
import java.util.Map;

public class Persion {
	private String lastName;
	private String age;
	private boolean boss;
	private Date birth;

	private Map<String, Object> map;
	private Dog dog;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public boolean isBoss() {
		return boss;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	@Override
	public String toString() {
		return "Persion [lastName=" + lastName + ", age=" + age + ", boss=" + boss + ", birth=" + birth + ", map=" + map
				+ ", dog=" + dog + "]";
	}

}
