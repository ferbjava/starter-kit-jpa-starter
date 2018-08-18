package com.capgemini.types;

import java.util.ArrayList;
import java.util.List;

public class PositionTO {

	private Long id;
	private String position;
	private List<EmployeeTO> employees = new ArrayList<>();

	public PositionTO(Long id, String position, List<EmployeeTO> employees) {
		super();
		this.id = id;
		this.position = position;
		this.employees.addAll(employees);
	}

	public PositionTO(String position, List<EmployeeTO> employees) {
		super();
		this.position = position;
		this.employees.addAll(employees);
	}

	public Long getId() {
		return id;
	}

	public String getPosition() {
		return position;
	}

	public static PositionTOBuilder builder() {
		return new PositionTOBuilder();
	}

	public List<EmployeeTO> getEmployees() {
		if (employees == null) {
			return null;
		}
		return employees;
	}

	public void setEmployees(List<EmployeeTO> employees) {
		this.employees.addAll(employees);
	}

	public static class PositionTOBuilder {
		private Long id;
		private String position;
		private List<EmployeeTO> employees = new ArrayList<>();

		public PositionTOBuilder() {
			super();
		}

		public PositionTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public PositionTOBuilder withPosition(String position) {
			this.position = position;
			return this;
		}

		public PositionTOBuilder withEmployees(List<EmployeeTO> employees) {
			this.employees.addAll(employees);
			return this;
		}

		public PositionTO build() {
			checkBeforeBuild(position);
			return new PositionTO(id, position, employees);
		}

		private void checkBeforeBuild(String position) {
			if (position == null || position.isEmpty()) {
				throw new RuntimeException("Incorrect 'Position' to be created");
			}
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionTO other = (PositionTO) obj;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PositionTO [id=" + id + ", position=" + position + ", employees=" + employees + "]";
	}

}
