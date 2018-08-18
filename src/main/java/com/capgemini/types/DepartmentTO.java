package com.capgemini.types;

import java.util.HashSet;
import java.util.Set;

public class DepartmentTO {

	private Long id;
	private String adress;
	private Integer phoneNumber;
	private String email;
	private Set<EmployeeTO> employees = new HashSet<>();

	public DepartmentTO() {
		super();
	}

	public DepartmentTO(Long id, String adress, Integer phoneNumber, String email, Set<EmployeeTO> employees) {
		super();
		this.id = id;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.employees = employees;
	}

	public DepartmentTO(String adress, Integer phoneNumber, String email, Set<EmployeeTO> employees) {
		super();
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public String getAdress() {
		return adress;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public Set<EmployeeTO> getEmployees() {
		return employees;
	}

	public static DepartmentTOBuilder builder() {
		return new DepartmentTOBuilder();
	}

	public static class DepartmentTOBuilder {
		private Long id;
		private String adress;
		private Integer phoneNumber;
		private String email;
		private Set<EmployeeTO> employees = new HashSet<>();

		public DepartmentTOBuilder() {
			super();
		}

		public DepartmentTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public DepartmentTOBuilder withAdress(String adress) {
			this.adress = adress;
			return this;
		}

		public DepartmentTOBuilder withPhoneNumber(Integer phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public DepartmentTOBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		public DepartmentTOBuilder withEmployees(Set<EmployeeTO> employees) {
			this.employees.addAll(employees);
			return this;
		}

		public DepartmentTO build() {
			checkBeforeBuild(adress, phoneNumber, email);
			return new DepartmentTO(id, adress, phoneNumber, email, employees);
		}

		private void checkBeforeBuild(String adress, Integer phoneNumber, String email) {
			if (adress == null || adress.isEmpty() || phoneNumber == null || email == null || email.isEmpty()) {
				throw new RuntimeException("Incorrect 'Position' to be created");
			}
		}
	}

	@Override
	public String toString() {
		return "DepartmentTO [id=" + id + ", adress=" + adress + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", employees=" + employees + "]";
	}

}
