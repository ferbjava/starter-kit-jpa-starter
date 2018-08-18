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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.capgemini.Listeners.CreateListener;
import com.capgemini.Listeners.UpdateListener;

@Entity
@EntityListeners({ CreateListener.class, UpdateListener.class })
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "CLIENT")
public class ClientEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 20)
	private String firstName;
	@Column(nullable = false, length = 20)
	private String lastName;
	@Column(nullable = false, length = 45)
	private String adress;
	@Column(nullable = false, length = 45)
	private String email;
	@Column(nullable = false)
	private Calendar dateBirth;
	@Column(nullable = false)
	private Integer phoneNumber;
	@Column(nullable = false, length = 45)
	private String creditCard;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "client_id")
	private List<RentalEntity> rentals = new ArrayList<>();

	public ClientEntity() {
	}

	public ClientEntity(Long id, String firstName, String lastName, String adress, String email, Calendar dateBirth,
			Integer phoneNumber, String creditCard, List<RentalEntity> rentals) {
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

	public ClientEntity(String firstName, String lastName, String adress, String email, Calendar dateBirth,
			Integer phoneNumber, String creditCard, List<RentalEntity> rentals) {
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Calendar dateBirth) {
		this.dateBirth = dateBirth;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public List<RentalEntity> getRentals() {
		return rentals;
	}

	public void setRentals(List<RentalEntity> rentals) {
		this.rentals = rentals;
	}

	public void addRental(RentalEntity rental) {
		this.rentals.add(rental);
	}

	@Override
	public String toString() {
		return "ClientEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", adress=" + adress
				+ ", email=" + email + ", dateBirth=" + dateBirth.getTime() + ", phoneNumber=" + phoneNumber + ", creditCard="
				+ creditCard + ", rentals=" + rentals + "]";
	}

}
