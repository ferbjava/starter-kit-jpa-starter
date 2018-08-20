package com.capgemini.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.capgemini.Listeners.CreateListener;
import com.capgemini.Listeners.UpdateListener;

@Entity
@EntityListeners({ CreateListener.class, UpdateListener.class })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "RENTAL")
public class RentalEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private Calendar dateStart;
	@Column(nullable = true)
	private Calendar dateStop;
	@Column(nullable = false)
	private Double charge;

	// for hibernate
	public RentalEntity() {
	}

	public RentalEntity(Calendar dateStart, Calendar dateStop, Double charge) {
		super();
		this.dateStart = dateStart;
		this.dateStop = dateStop;
		this.charge = charge;
	}

	public Long getId() {
		return id;
	}

	public Calendar getDateStart() {
		return dateStart;
	}

	public void setDateStart(Calendar dateStart) {
		this.dateStart = dateStart;
	}

	public Calendar getDateStop() {
		return dateStop;
	}

	public void setDateStop(Calendar dateStop) {
		this.dateStop = dateStop;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}
	
}
