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

	public String getLastName() {
		return lastName;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public PositionTO getPosition() {
		return position;
	}

	public static EmployeeTOBuilder builder() {
		return new EmployeeTOBuilder();
	}

	public static class EmployeeTOBuilder {

		private Long id;
		private String firstName;
		private String lastName;
		private Date dateBirth;
		private PositionTO position;

		public EmployeeTOBuilder() {
			super();
		}

		public EmployeeTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public EmployeeTOBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public EmployeeTOBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public EmployeeTOBuilder withDateBirth(Date dateBirth) {
			this.dateBirth = dateBirth;
			return this;
		}

		public EmployeeTOBuilder withPosition(PositionTO position) {
			this.position = position;
			return this;
		}

		public EmployeeTO build() {
			checkBeforeBuild(firstName, lastName, dateBirth, position);
			return new EmployeeTO(id, firstName, lastName, dateBirth, position);
		}

		private void checkBeforeBuild(String firstName, String lastName, Date dateBirth, PositionTO position) {
			boolean isFirstName = false;
			if (firstName != null && !firstName.isEmpty()) {
				isFirstName = true;
			}
			boolean isLastName = false;
			if (lastName != null && !lastName.isEmpty()) {
				isLastName = true;
			}
			boolean isDateBirth = false;
			if (!dateBirth.equals(null)) {
				isDateBirth = true;
			}
			boolean isPosition = false;
			if (!position.equals(null)) {
				isPosition = true;
			}

			if (!isFirstName || !isLastName || !isDateBirth || !isPosition) {
				throw new RuntimeException("Incorrect 'Employee' to be created");
			}
		}
	}

	@Override
	public String toString() {
		return "EmployeeTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateBirth="
				+ dateBirth + ", position=" + position + "]";
	}
	
}
