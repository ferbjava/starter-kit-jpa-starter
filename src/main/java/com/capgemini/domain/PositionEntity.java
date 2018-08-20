package com.capgemini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
@Table(name = "Position")
public class PositionEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 45)
	private String position;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "position_id")
	private List<EmployeeEntity> employees = new ArrayList<>();

	// for hibernate
	public PositionEntity() {
	}

	public PositionEntity(Long id, String position, List<EmployeeEntity> employees) {
		super();
		this.id = id;
		this.position = position;
		this.employees = employees;
	}

	public PositionEntity(String position, List<EmployeeEntity> employees) {
		super();
		this.position = position;
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public PositionEntity removeEmployee(Long id) {
		for(EmployeeEntity e:employees){
			if(e.getId().equals(id)){
				this.employees.remove(e);
				return this;
			}
		}
		return this;
	}

	@Override
	public String toString() {
		return "PositionEntity [id=" + id + ", position=" + position + ", employees=" + employees + "]";
	}

}
