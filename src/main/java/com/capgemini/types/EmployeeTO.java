package com.capgemini.types;

import java.util.Date;

public class EmployeeTO {

	private Long id;
	private String firstName;
	private String lastName;
	private Date dateBirth;
	private PositionTO position;

	public EmployeeTO() {
	}

	public EmployeeTO(Long id, String firstName, String lastName, Date dateBirth, PositionTO position) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.position = position;
	}

	public EmployeeTO(String firstName, String lastName, Date dateBirth, PositionTO position) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public PositionTO getPosition() {
		return position;
	}

	public void setPosition(PositionTO position) {
		this.position = position;
	}

}
