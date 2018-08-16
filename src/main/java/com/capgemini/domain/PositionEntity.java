package com.capgemini.domain;

import java.io.Serializable;

import javax.persistence.*;

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

	// for hibernate
	public PositionEntity() {
	}

	public PositionEntity(String position) {
		super();
		this.setPosition(position);
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

}
