package com.capgemini.domain;

import java.io.Serializable;
import java.sql.Timestamp;

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
	private Timestamp dateStart;
	@Column(nullable = true)
	private Timestamp dateStop;
	@Column(nullable = false)
	private Double charge;

	// for hibernate
	public RentalEntity() {
	}

	public RentalEntity(Long id, Timestamp dateStart, Timestamp dateStop, Double charge) {
		super();
		this.id = id;
		this.dateStart = dateStart;
		this.dateStop = dateStop;
		this.charge = charge;
	}

	public RentalEntity(Timestamp dateStart, Timestamp dateStop, Double charge) {
		super();
		this.dateStart = dateStart;
		this.dateStop = dateStop;
		this.charge = charge;
	}

	public Long getId() {
		return id;
	}

	public Timestamp getDateStart() {
		return dateStart;
	}

	public void setDateStart(Timestamp dateStart) {
		this.dateStart = dateStart;
	}

	public Timestamp getDateStop() {
		return dateStop;
	}

	public void setDateStop(Timestamp dateStop) {
		this.dateStop = dateStop;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	@Override
	public String toString() {
		return "RentalEntity [id=" + id + ", dateStart=" + dateStart + ", dateStop=" + dateStop + ", charge=" + charge
				+ "]";
	}

}
