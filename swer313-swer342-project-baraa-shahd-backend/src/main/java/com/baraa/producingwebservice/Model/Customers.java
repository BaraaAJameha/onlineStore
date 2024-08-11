package com.baraa.producingwebservice.Model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;


@Entity
@Table(name = "customers")
public class Customers {
	@Column(name = "customerNumber")
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerNumber;
	@Size(min = 1, message = "Customer Name should have at least one characters")
	@Column(name = "customerName")
	private String customerName;
	@Column(name = "contactFirstName")
	private String contactFirstName;
	@Column(name = "contactLastName")
	private String contactLastName;
	@Column(name = "phone")
	private String phone;
	@Column(name = "addressLine1")
	private String adressLine1;
	@Column(name = "addressLine2")
	private String adressLine2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "postalCode")
	private String postalCode;
	@Column(name = "country")
	private String country;	
	
//	@Column(name = "salesRepEmployeeNumber", nullable=true)
//	private int salesRepEmployeeNumber;
	@Column(name = "creditLimit")
	private double creditLimit;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerNumber")
	private List<Payments> payments;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerNumber")
	private List<Orders> orders;

	public Customers() {
		super();
	}

	public Customers(@NotNull Integer customerNumber,
			@Size(min = 1, message = "Customer Name should have at least one characters") String customerName,
			String contactFirstName, String contactLastName, String phone, String adressLine1, String adressLine2,
			String city, String state, String postalCode, String country, int salesRepEmployeeNumber,
			double creditLimit, List<Payments> payments, List<Orders> orders) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.phone = phone;
		this.adressLine1 = adressLine1;
		this.adressLine2 = adressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
//		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
		this.creditLimit = creditLimit;
		this.payments = payments;
		this.orders = orders;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerFirstName() {
		return contactFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.contactFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return contactLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.contactLastName = customerLastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdressLine1() {
		return adressLine1;
	}

	public void setAdressLine1(String adressLine1) {
		this.adressLine1 = adressLine1;
	}

	public String getAdressLine2() {
		return adressLine2;
	}

	public void setAdressLine2(String adressLine2) {
		this.adressLine2 = adressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

//	public int getSalesRepEmployeeNumber() {
//		return salesRepEmployeeNumber;
//	}
//
//	public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
//		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
//	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adressLine1, adressLine2, city, contactFirstName, contactLastName, country, creditLimit,
				customerName, customerNumber, phone, postalCode, /* salesRepEmployeeNumber, */ state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customers other = (Customers) obj;
		return Objects.equals(adressLine1, other.adressLine1) && Objects.equals(adressLine2, other.adressLine2)
				&& Objects.equals(city, other.city) && Objects.equals(contactFirstName, other.contactFirstName)
				&& Objects.equals(contactLastName, other.contactLastName) && Objects.equals(country, other.country)
				&& Double.doubleToLongBits(creditLimit) == Double.doubleToLongBits(other.creditLimit)
				&& Objects.equals(customerName, other.customerName)
				&& Objects.equals(customerNumber, other.customerNumber) && Objects.equals(phone, other.phone)
				&& Objects.equals(postalCode, other.postalCode)
				/* && salesRepEmployeeNumber == other.salesRepEmployeeNumber */ && Objects.equals(state, other.state);
	}

	@Override
	public String toString() {
		return "Customers [customerNumber=" + customerNumber + ", customerName=" + customerName + ", contactFirstName="
				+ contactFirstName + ", contactLastName=" + contactLastName + ", phone=" + phone + ", adressLine1="
				+ adressLine1 + ", adressLine2=" + adressLine2 + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + ", country="
				+ country/* + ", salesRepEmployeeNumber=" + salesRepEmployeeNumber */
				+ ", creditLimit=" + creditLimit + "]";
	}

}
