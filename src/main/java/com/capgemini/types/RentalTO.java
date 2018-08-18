package com.capgemini.types;

import java.sql.Timestamp;

public class RentalTO {

	private Long id;
	private Timestamp dateStart;
	private Timestamp dateStop;
	private Double charge;

	public RentalTO(Long id, Timestamp dateStart, Timestamp dateStop, Double charge) {
		super();
		this.id = id;
		this.dateStart = dateStart;
		this.dateStop = dateStop;
		this.charge = charge;
	}

	public RentalTO(Timestamp dateStart, Timestamp dateStop, Double charge) {
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

	public Timestamp getDateStop() {
		return dateStop;
	}

	public Double getCharge() {
		return charge;
	}

	public static RentalTOBuilder builder() {
		return new RentalTOBuilder();
	}

	public static class RentalTOBuilder {

		private Long id;
		private Timestamp dateStart;
		private Timestamp dateStop;
		private Double charge;

		public RentalTOBuilder() {
			super();
		}

		public RentalTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public RentalTOBuilder withDateStart(Timestamp dateStart) {
			this.dateStart = dateStart;
			return this;
		}

		public RentalTOBuilder withDateStop(Timestamp dateStop) {
			this.dateStop = dateStop;
			return this;
		}

		public RentalTOBuilder withCharge(Double charge) {
			this.charge = charge;
			return this;
		}

		public RentalTO build() {
			checkBeforeBuild(dateStart, dateStop, charge);
			return new RentalTO(id, dateStart, dateStop, charge);
		}

		private void checkBeforeBuild(Timestamp dateStart, Timestamp dateStop, Double charge) {
			if (dateStart != null && dateStop != null && charge != null) {
				throw new RuntimeException("Incorrect 'Rental' to be created");
			}
		}
	}

}
