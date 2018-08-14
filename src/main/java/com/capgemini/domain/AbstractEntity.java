package com.capgemini.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, updatable = false)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false)
	private Date updated;

	public void setCreatedAt(final Timestamp date) {
		this.created=date;
	}
	
	public void setUpdatedAt(final Timestamp date) {
		this.updated=date;
	}

	public Date getCreatedAt() {
		return created;
	}

	public Date getUpdatedAt() {
		return updated;
	}
	

}
