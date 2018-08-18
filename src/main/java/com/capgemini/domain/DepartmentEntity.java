package com.capgemini.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Set<EmployeeEntity> employees = new HashSet<>();

	// for hibernate
	public DepartmentEntity() {
	}

	public DepartmentEntity(Long id, String adress, Integer phoneNumber, String email, Set<EmployeeEntity> employees) {
		super();
		this.id = id;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.employees = employees;
	}

	public DepartmentEntity(String adress, Integer phoneNumber, String email, Set<EmployeeEntity> employees) {
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

	public Set<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public void addEmployee(EmployeeEntity employee) {
		this.employees.add(employee);
	}

	@Override
	public String toString() {
		return "DepartmentEntity [id=" + id + ", adress=" + adress + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", employees=" + employees + "]";
	}

}
