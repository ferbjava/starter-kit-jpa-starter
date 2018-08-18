package com.capgemini.types;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClientTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String adress;
	private String email;
	private Calendar dateBirth;
	private Integer phoneNumber;
	private String creditCard;
	private List<RentalTO> rentals = new ArrayList<>();

	public ClientTO(Long id, String firstName, String lastName, String adress, String email, Calendar dateBirth,
			Integer phoneNumber, String creditCard, List<RentalTO> rentals) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.email = email;
		this.dateBirth = dateBirth;
		this.phoneNumber = phoneNumber;
		this.creditCard = creditCard;
		this.rentals = rentals;
	}

	public ClientTO(String firstName, String lastName, String adress, String email, Calendar dateBirth,
			Integer phoneNumber, String creditCard, List<RentalTO> rentals) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.email = email;
		this.dateBirth = dateBirth;
		this.phoneNumber = phoneNumber;
		this.creditCard = creditCard;
		this.rentals = rentals;
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

	public Calendar getDateBirth() {
		return dateBirth;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public List<RentalTO> getRentals() {
		return rentals;
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
		private Calendar dateBirth;
		private Integer phoneNumber;
		private String creditCard;
		private List<RentalTO> rentals = new ArrayList<>();

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

		public ClientTOBuilder withBirthDate(Calendar date) {
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

		public ClientTOBuilder withRentalsTO(List<RentalTO> rentals) {
			this.rentals = rentals;
			return this;
		}

		public ClientTO build() {
			checkBeforeBuild(firstName, lastName, adress, email, dateBirth, phoneNumber, creditCard);
			return new ClientTO(id, firstName, lastName, adress, email, dateBirth, phoneNumber, creditCard, rentals);
		}

		private void checkBeforeBuild(String firstName, String lastName, String adress, String email,
				Calendar dateBirth, Integer phoneNumber, String creditCard) {

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
	}

	@Override
	public String toString() {
		return "ClientTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", adress=" + adress
				+ ", email=" + email + ", dateBirth=" + dateBirth.getTime() + ", phoneNumber=" + phoneNumber + ", creditCard="
				+ creditCard + ", rentals=" + rentals + "]";
	}

}
