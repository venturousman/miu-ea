package cs544.message;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;

	public Person(String name) {
		super();
		this.name = name;
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
