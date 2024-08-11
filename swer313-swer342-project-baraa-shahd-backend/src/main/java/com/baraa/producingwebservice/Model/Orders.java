package com.baraa.producingwebservice.Model;

import java.util.Date;
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

@Entity
@Table(name = "orders")
public class Orders {
	@NotNull
	@Column(name = "orderNumber")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderNumber;
	@Column(name = "orderDate")
	private Date orderDate;
	@Column(name = "requiredDate")
	private Date requiredDate;
	@Column(name = "shippedDate")
	private Date shippedDate;
	@Column(name = "status")
	private String status;
	@Column(name = "comments")
	private String comments;
	@NotNull
	@Column(name = "customerNumber ")
	private Integer customerNumber;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "OrderNumber")
	private List<OrderDetails> orders;

	public Orders() {
		super();
	}

	public Orders(@NotNull Integer orderNumber, Date orderDate, Date requiredDate, Date shippedDate, String status,
			String comments, @NotNull Integer customerNumber, List<OrderDetails> orders) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comments = comments;
		this.customerNumber = customerNumber;
		this.orders = orders;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comments, customerNumber, orderDate, orderNumber, requiredDate, shippedDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(comments, other.comments) && Objects.equals(customerNumber, other.customerNumber)
				&& Objects.equals(orderDate, other.orderDate) && Objects.equals(orderNumber, other.orderNumber)
				&& Objects.equals(requiredDate, other.requiredDate) && Objects.equals(shippedDate, other.shippedDate)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Orders [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate
				+ ", shippedDate=" + shippedDate + ", status=" + status + ", comments=" + comments + ", customerNumber="
				+ customerNumber + "]";
	}

}
