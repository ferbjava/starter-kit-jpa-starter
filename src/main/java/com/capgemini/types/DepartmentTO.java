package com.capgemini.types;

import java.util.ArrayList;
import java.util.List;

public class DepartmentTO {

	private Long id;
	private String adress;
	private Integer phoneNumber;
	private String email;
	private List<EmployeeTO> employees = new ArrayList<>();
	private List<RentalTO> rentalsStart = new ArrayList<>();
	private List<RentalTO> rentalsStop = new ArrayList<>();

	public DepartmentTO() {
		super();
	}

	public DepartmentTO(Long id, String adress, Integer phoneNumber, String email, List<EmployeeTO> employees,
			List<RentalTO> rentalsStart, List<RentalTO> rentalsStop) {
		super();
		this.id = id;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.employees = employees;
		this.rentalsStart = rentalsStart;
		this.rentalsStop = rentalsStop;
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

	public List<EmployeeTO> getEmployees() {
		return employees;
	}

	public List<RentalTO> getRentalsStart() {
		return rentalsStart;
	}

	public List<RentalTO> getRentalsStop() {
		return rentalsStop;
	}

	public static DepartmentTOBuilder builder() {
		return new DepartmentTOBuilder();
	}

	public static class DepartmentTOBuilder {
		private Long id;
		private String adress;
		private Integer phoneNumber;
		private String email;
		private List<EmployeeTO> employees = new ArrayList<>();
		private List<RentalTO> rentalsStart = new ArrayList<>();
		private List<RentalTO> rentalsStop = new ArrayList<>();

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

		public DepartmentTOBuilder withEmployees(List<EmployeeTO> employees) {
			this.employees.addAll(employees);
			return this;
		}

		public DepartmentTOBuilder withRentalsStart(List<RentalTO> rentalsStart) {
			this.rentalsStart.addAll(rentalsStart);
			return this;
		}

		public DepartmentTOBuilder withRentalsStop(List<RentalTO> rentalsStop) {
			this.rentalsStop.addAll(rentalsStop);
			return this;
		}

		public DepartmentTO build() {
			checkBeforeBuild(adress, phoneNumber, email);
			return new DepartmentTO(id, adress, phoneNumber, email, employees, rentalsStart, rentalsStop);
		}

		private void checkBeforeBuild(String adress, Integer phoneNumber, String email) {
			if (adress == null || adress.isEmpty() || phoneNumber == null || email == null || email.isEmpty()) {
				throw new RuntimeException("Incorrect 'Position' to be created");
			}
		}
	}
	
}
