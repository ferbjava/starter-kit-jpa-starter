package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.capgemini.Listeners.CreateListener;
import com.capgemini.Listeners.UpdateListener;

@Entity
@EntityListeners({ CreateListener.class, UpdateListener.class })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "EMPLOYEE")
public class EmployeeEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 20)
	private String firstName;
	@Column(nullable = false, length = 20)
	private String lastName;
	@Column(nullable = false)
	private Calendar dateBirth;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "CARER_CAR", joinColumns = {
			@JoinColumn(name = "EMPLOYEE_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CAR_ID", nullable = false, updatable = false) })
	private List<CarEntity> cars = new ArrayList<>();

	public EmployeeEntity() {
	}

	public EmployeeEntity(Long id, String firstName, String lastName, Calendar dateBirth, List<CarEntity> cars) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.cars = cars;
	}

	public EmployeeEntity(String firstName, String lastName, Calendar dateBirth, List<CarEntity> cars) {
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Calendar getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Calendar dateBirth) {
		this.dateBirth = dateBirth;
	}

	public List<CarEntity> getCars() {
		return cars;
	}

	public void setCars(List<CarEntity> cars) {
		this.cars = cars;
	}
	
	public void addCar(CarEntity car){
		this.cars.add(car);
	}
	
	public EmployeeEntity removeCarById(Long id){
		for(CarEntity c:cars){
			if(c.getId().equals(id)){
				this.cars.remove(c);
				return this;
			}
		}
		return this;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateBirth="
				+ dateBirth + ", cars=" + cars + "]";
	}

}
