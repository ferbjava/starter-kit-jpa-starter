package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.capgemini.Listeners.CreateListener;
import com.capgemini.Listeners.UpdateListener;

@Entity
@EntityListeners({ CreateListener.class, UpdateListener.class })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "DEPARTMENT")
public class DepartmentEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 45)
	private String adress;
	@Column(nullable = false)
	private Integer phoneNumber;
	@Column(nullable = false, length = 45)
	private String email;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "department_id")
	private List<EmployeeEntity> employees = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "start_depertment_id")
	private List<RentalEntity> rentalsStart = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "stop_depertment_id")
	private List<RentalEntity> rentalsStop = new ArrayList<>();

	// for hibernate
	public DepartmentEntity() {
	}

	public DepartmentEntity(Long id, String adress, Integer phoneNumber, String email, List<EmployeeEntity> employees, List<RentalEntity> rentalsStart, List<RentalEntity> rentalsStop) {
		super();
		this.id = id;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.employees = employees;
		this.rentalsStart = rentalsStart;
		this.rentalsStop = rentalsStop;
	}

	public DepartmentEntity(String adress, Integer phoneNumber, String email, List<EmployeeEntity> employees, List<RentalEntity> rentalsStart, List<RentalEntity> rentalsStop) {
		super();
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

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public void addEmployee(EmployeeEntity employee) {
		this.employees.add(employee);
	}

	public List<RentalEntity> getRentalsStart() {
		return rentalsStart;
	}

	public void setRentalsStart(List<RentalEntity> rentalsStart) {
		this.rentalsStart = rentalsStart;
	}

	public void addRentalStart(RentalEntity rentalStart) {
		this.rentalsStart.add(rentalStart);
	}

	public List<RentalEntity> getRentalsStop() {
		return rentalsStop;
	}

	public void setRentalsStop(List<RentalEntity> rentalsStop) {
		this.rentalsStop = rentalsStop;
	}

	public void addRentalStop(RentalEntity rentalStop) {
		this.rentalsStop.add(rentalStop);
	}
	
	@Override
	public String toString() {
		return "DepartmentEntity [id=" + id + ", adress=" + adress + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", employees=" + employees + ", rentalsStart=" + rentalsStart + ", rentalsStop=" + rentalsStop + "]";
	}
	
}
