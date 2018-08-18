package com.capgemini.types;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class EmployeeTO {

	private Long id;
	private String firstName;
	private String lastName;
	private Calendar dateBirth;
	private Set<CarTO> cars = new HashSet<>();

	public EmployeeTO() {
	}

	public EmployeeTO(Long id, String firstName, String lastName, Calendar dateBirth,
			Set<CarTO> cars) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.cars = cars;
	}

	public EmployeeTO(String firstName, String lastName, Calendar dateBirth, Set<CarTO> cars) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.cars = cars;
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

	public Calendar getDateBirth() {
		return dateBirth;
	}

	public Set<CarTO> getCars() {
		return cars;
	}

	public static EmployeeTOBuilder builder() {
		return new EmployeeTOBuilder();
	}

	public static class EmployeeTOBuilder {

		private Long id;
		private String firstName;
		private String lastName;
		private Calendar dateBirth;
		private Set<CarTO> cars;

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

		public EmployeeTOBuilder withDateBirth(Calendar dateBirth) {
			this.dateBirth = dateBirth;
			return this;
		}

		public EmployeeTOBuilder withCars(Set<CarTO> cars) {
			this.cars = cars;
			return this;
		}

		public EmployeeTO build() {
			checkBeforeBuild(firstName, lastName, dateBirth);
			return new EmployeeTO(id, firstName, lastName, dateBirth, cars);
		}

		private void checkBeforeBuild(String firstName, String lastName, Calendar dateBirth) {
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

			if (!isFirstName || !isLastName || !isDateBirth) {
				throw new RuntimeException("Incorrect 'Employee' to be created");
			}
		}
	}

	@Override
	public String toString() {
		return "EmployeeTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateBirth="
				+ dateBirth + ", cars=" + cars + "]";
	}
	

}
