package com.capgemini.types;

import java.sql.Timestamp;

public class ClientTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String adress;
	private String email;
	private Timestamp dateBirth;
	private Integer phoneNumber;
	private String creditCard;
	private RentalTO rental;

	public ClientTO(Long id, String firstName, String lastName, String adress, String email, Timestamp dateBirth,
			Integer phoneNumber, String creditCard, RentalTO rental) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.email = email;
		this.dateBirth = dateBirth;
		this.phoneNumber = phoneNumber;
		this.creditCard = creditCard;
		this.rental = rental;
	}

	public ClientTO(String firstName, String lastName, String adress, String email, Timestamp dateBirth,
			Integer phoneNumber, String creditCard, RentalTO rental) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.email = email;
		this.dateBirth = dateBirth;
		this.phoneNumber = phoneNumber;
		this.creditCard = creditCard;
		this.rental = rental;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAdress() {
		return adress;
	}

	public String getEmail() {
		return email;
	}

	public Timestamp getDateBirth() {
		return dateBirth;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public String getCreditCard() {
		return creditCard;
	}
	
	public RentalTO getRental() {
		return rental;
	}

	public static ClientTOBuilder builder() {
		return new ClientTOBuilder();
	}

	public static class ClientTOBuilder {

		private Long id;
		private String firstName;
		private String lastName;
		private String adress;
		private String email;
		private Timestamp dateBirth;
		private Integer phoneNumber;
		private String creditCard;
		private RentalTO rental;

		public ClientTOBuilder() {
			super();
		}

		public ClientTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public ClientTOBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public ClientTOBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public ClientTOBuilder withAdress(String adress) {
			this.adress = adress;
			return this;
		}

		public ClientTOBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		public ClientTOBuilder withBirthDate(Timestamp date) {
			this.dateBirth = date;
			return this;
		}

		public ClientTOBuilder withPhoneNumber(Integer phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public ClientTOBuilder withCreditCard(String creditCard) {
			this.creditCard = creditCard;
			return this;
		}

		public ClientTOBuilder withRentalTO(RentalTO rental) {
			this.rental = rental;
			return this;
		}

		public ClientTO build() {
			checkBeforeBuild(firstName, lastName, adress, email, dateBirth, phoneNumber, creditCard);
			return new ClientTO(id, firstName, lastName, adress, email, dateBirth, phoneNumber, creditCard, rental);
		}

		private void checkBeforeBuild(String firstName, String lastName, String adress, String email,
				Timestamp dateBirth, Integer phoneNumber, String creditCard) {

			boolean isFirstName = false;
			if (firstName != null && !firstName.isEmpty()) {
				isFirstName = true;
			}
			boolean isLastName = false;
			if (lastName != null && !lastName.isEmpty()) {
				isLastName = true;
			}
			boolean isAdress = false;
			if (adress != null && !adress.isEmpty()) {
				isAdress = true;
			}
			boolean isEmail = false;
			if (email != null && !email.isEmpty()) {
				isEmail = true;
			}
			boolean isDateBirth = false;
			if (dateBirth != null) {
				isDateBirth = true;
			}
			boolean isPhoneNumber = false;
			if (phoneNumber != null) {
				isPhoneNumber = true;
			}
			boolean isCreditCard = false;
			if (creditCard != null && !creditCard.isEmpty()) {
				isCreditCard = true;
			}
			if (!isFirstName || !isLastName || !isAdress || !isEmail || !isDateBirth || !isPhoneNumber
					|| !isCreditCard) {
				throw new RuntimeException("Incorrect 'Car' to be created");
			}
		}
		
		@Override
		public String toString() {
			return "ClientTOBuilder [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", adress="
					+ adress + ", email=" + email + ", dateBirth=" + dateBirth + ", phoneNumber=" + phoneNumber
					+ ", creditCard=" + creditCard + ", rental=" + rental + "]";
		}
	}

}
