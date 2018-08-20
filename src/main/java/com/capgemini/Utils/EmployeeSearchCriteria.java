package com.capgemini.Utils;

public class EmployeeSearchCriteria {
	
	private Long carId;
	private Long departmentId;
	private Long positionId;
	
	public EmployeeSearchCriteria(Long carId, Long departmentId, Long positionId){
		this.setCarId(carId);
		this.setDepartmentId(departmentId);
		this.setPositionId(positionId);
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	
}
