package com.znsx.test.bo;

/**
 * User
 * 
 * @author huangbuji
 *         <p />
 *         Create at 2014-3-7 上午9:31:13
 */
public class User {
	private Integer id;
	private String name;
	private int age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "id:" + this.id + ",name:" + this.name + ",age:" + this.age;
	}
}
